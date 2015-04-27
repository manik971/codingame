import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Skynet: the Virus
 */
class Player {

    /**
     * Link between two nodes
     */
    public class Link {

        private final int _nodeA;
        private final int _nodeB;

        public Link(int nodeA_, int nodeB_) {
            _nodeA = nodeA_;
            _nodeB = nodeB_;
        }

        /**
         * Sever a link
         */
        public void sever() {
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            return _nodeA + " " + _nodeB;
        }

        @Override
        public boolean equals(Object other_) {
            Link otherLink = (Link) other_;
            return ((_nodeA == otherLink._nodeA && _nodeB == otherLink._nodeB) || (_nodeB == otherLink._nodeA && _nodeA == otherLink._nodeB));
        }

        public int getNodeA() {
            return this._nodeA;
        }

        public int getNodeB() {
            return this._nodeB;
        }
    }

    /**
     * Subnetwork : List of nodes connected only to a gate or to each other
     */
    public class Subnet {

        // nodes in the subnet
        private final List<Integer> _nodes;
        // input and output links
        private final Stack<Link> _inputOutput;
        // subnet open status
        // 0:closed 1:closing 2:open
        private int _status;

        public Subnet() {
            this._nodes = new ArrayList<Integer>();
            this._inputOutput = new Stack<Link>();
            this._status = 2;
        }

        /**
         * Get a input or a output link to close this subnet
         * 
         * @param links_
         *            list of links
         * @param gateways_
         *            list of gateways
         * @return a input or output link
         */
        public Link getInputOutputLink(List<Link> links_, List<Integer> gateways_) {
            // first access to this subnet
            if (this._status == 2) {
                this.initInputOuputLinks(links_, gateways_);
                this._status = 1;
            }
            Link link = this._inputOutput.pop();
            // close the subnet if there is no more input output
            if (this._inputOutput.empty()) {
                this._status = 0;
            }
            return link;
        }

        /**
         * Initialize the subnet input/output links
         * 
         * @param links_
         *            list of links
         * @param gateways_
         *            list of gateways
         */
        public void initInputOuputLinks(List<Link> links_, List<Integer> gateways_) {
            for (Link link : links_) {
                if (this._nodes.contains(link.getNodeA()) || this._nodes.contains(link.getNodeB())) {
                    if (!this._nodes.contains(link.getNodeA()) || !this._nodes.contains(link.getNodeB())) {
                        if (!gateways_.contains(link.getNodeA()) && !gateways_.contains(link.getNodeB())) {
                            this._inputOutput.push(link);
                        }
                    }
                }
            }
        }

        /**
         * Add a node to the subnet
         * 
         * @param node_
         *            a node
         */
        public void addNode(Integer node_) {
            this._nodes.add(node_);
        }

        public int getNbNodes() {
            return this._nodes.size();
        }

        public int getStatus() {
            return this._status;
        }
    }

    /**
     * Main
     */
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Player player = new Player();

        // number of nodes
        int nbNodes = in.nextInt();
        // number of links
        int nbLinks = in.nextInt();
        // number of exit gateways
        int nbGateways = in.nextInt();
        // links
        List<Link> links = new ArrayList<Link>(nbLinks);
        // exit gateways
        List<Integer> gateways = new ArrayList<Integer>(nbGateways);
        // number of connections for each node
        List<AtomicInteger> nbConnections = new ArrayList<AtomicInteger>(nbNodes);
        // one subnet for each gateway
        Map<Integer, Subnet> subnets = new HashMap<Integer, Subnet>();

        // initialize connection counters
        for (int i = 0; i < nbNodes; i++) {
            nbConnections.add(i, new AtomicInteger(0));
        }

        // initialize links
        for (int i = 0; i < nbLinks; i++) {
            int nodeA = in.nextInt();
            int nodeB = in.nextInt();
            links.add(player.new Link(nodeA, nodeB));
            // update connection counters
            nbConnections.get(nodeA).incrementAndGet();
            nbConnections.get(nodeB).incrementAndGet();
        }

        // initialize exit gateways
        for (int j = 0; j < nbGateways; j++) {
            int gate = in.nextInt();
            gateways.add(gate);
            subnets.put(gate, player.new Subnet());
        }

        // initialize subnets
        // a node in the subnet can't have more than 3 connections
        for (Link link : links) {
            if (gateways.contains(link.getNodeA()) && nbConnections.get(link.getNodeB()).intValue() <= 3) {
                subnets.get(link.getNodeA()).addNode(link.getNodeB());
            }
            else if (gateways.contains(link.getNodeB()) && nbConnections.get(link.getNodeA()).intValue() <= 3) {
                subnets.get(link.getNodeB()).addNode(link.getNodeA());
            }
        }

        // game loop
        while (true) {
            int skynetPosition = in.nextInt();

            // block direct link to gateway
            boolean action = false;
            for (Integer gateway : gateways) {
                Link linkToTest = player.new Link(skynetPosition, gateway);
                int linkIndex = links.indexOf(linkToTest);
                if (linkIndex >= 0) {
                    severLink(links, nbConnections, linkIndex);
                    action = true;
                    break;
                }
            }

            // no incoming danger, block the best link
            if (!action) {
                // try to find a subnet to block
                Subnet subnetToBlock = null;
                int nbMax = 0;
                for (Map.Entry<Integer, Subnet> subnetEntry : subnets.entrySet()) {
                    // select in priority a subnet in closing progress
                    if (subnetEntry.getValue().getStatus() == 1) {
                        subnetToBlock = subnetEntry.getValue();
                        break;
                    }
                    // otherwise select the largest open subnet
                    else if (subnetEntry.getValue().getStatus() == 2 && subnetEntry.getValue().getNbNodes() > nbMax) {
                        subnetToBlock = subnetEntry.getValue();
                        nbMax = subnetEntry.getValue().getNbNodes();
                    }
                }
                // a subnet can be blocked
                if (subnetToBlock != null && subnetToBlock.getNbNodes() > 0) {
                    Link linkToBlock = subnetToBlock.getInputOutputLink(links, gateways);
                    severLink(links, nbConnections, links.indexOf(linkToBlock));
                }
                // otherwise block a random link
                else {
                    severLink(links, nbConnections, 0);
                }
            }
        }
    }

    /**
     * Sever a link
     * 
     * @param links_
     *            links of the current game
     * @param nbConnections_
     *            nodes connection counters
     * @param linkIndex_
     *            index of the link
     */
    public static void severLink(List<Link> links_, List<AtomicInteger> nbConnections_, int linkIndex_) {
        links_.get(linkIndex_).sever();
        nbConnections_.get(links_.get(linkIndex_).getNodeA()).decrementAndGet();
        nbConnections_.get(links_.get(linkIndex_).getNodeB()).decrementAndGet();
        links_.remove(linkIndex_);
    }
}