import java.util.*;
import java.io.*;
import java.math.*;
import java.util.concurrent.atomic.AtomicInteger;
 
class Player {
 
	public class Link {
 
		private int m_n1;
		private int m_n2;
 
		public Link(int n1, int n2) {
			m_n1 = n1;
			m_n2 = n2;
		}
 
		public void severs() {
			System.out.println(this.toString());
		}
		
		public String toString() {
			return m_n1 + " " + m_n2;
		}
 
		public boolean equals(Object o) {
			Link otherLink = (Link) o;
			return ((m_n1 == otherLink.m_n1 && m_n2 == otherLink.m_n2) || (m_n2 == otherLink.m_n1 && m_n1 == otherLink.m_n2));
		}
		
		public int getNodeA(){
		    return this.m_n1;
		}
		
		public int getNodeB(){
		    return this.m_n2;
		}
	}
	
	public class Subnet {
	    
	    private int gateParent;
	    private List<Integer> nodes;
	    private Stack<Link> input_output;
	    private int status;
	    
	    public Subnet(int gateParent_){
	        this.gateParent = gateParent_;
	        this.nodes = new ArrayList<Integer>();
	        this.input_output = new Stack<Link>();
	        this.status = 2; // 0:closed 1:in progress 2:open
	    }
	    
	    public Link getLink(List<Link> links_, List<Integer> gateways_){
	        if(this.status == 2){
	            this.searchInputOutput(links_, gateways_);
	            this.status = 1;
	        }
	        Link link = this.input_output.pop();
	        if(this.input_output.empty()){
	            this.status = 0;
	        }
	        return link;
	    }
	    
	    public void searchInputOutput(List<Link> links_, List<Integer> gateways_){
	        for(Link link : links_){
	            if(this.nodes.contains(link.getNodeA()) || this.nodes.contains(link.getNodeB())){
	                if(!this.nodes.contains(link.getNodeA()) || !this.nodes.contains(link.getNodeB())){
	                    if(!gateways_.contains(link.getNodeA()) && !gateways_.contains(link.getNodeB())){
	                        this.input_output.push(link);
	                    }
	                }
	            }
	        }
	    }
	    
	    public int getStatus(){
	        return this.status;
	    }
	    
	    public void print(){
	        System.err.println("--SUBNET--");
	        for(Integer node : this.nodes){
	            System.err.print(node+" ");
	        }
	        System.err.println(" ");
	    }
	    
	    public void addNode(Integer node_){
	        this.nodes.add(node_);
	    }
	    
	    public int getNbNodes(){
	        return this.nodes.size();
	    }
	}
 
	public boolean blockDirectLinkToGate(int agentNode, List<Integer> gateways,
			List<Link> links, List<AtomicInteger> nbConnections) {
			    
	    boolean ret = false;
 
		for (Integer gateway : gateways) {
			Link linkToTest = new Link(agentNode, gateway);
			int linkIndex = links.indexOf(linkToTest);
			if (linkIndex >= 0) {
				linkToTest.severs();
				links.remove(linkIndex);
				nbConnections.get(gateway).decrementAndGet();
				nbConnections.get(agentNode).decrementAndGet();
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Player player = new Player();
		// Read init information from standard input, if any
		int nbNodes = in.nextInt();
		int nbLinks = in.nextInt();
		int nbExits = in.nextInt();
 
		List<Link> links = new ArrayList<Link>(nbLinks);
		List<Integer> gateways = new ArrayList<Integer>(nbExits);
		List<AtomicInteger> nbConnections = new ArrayList<AtomicInteger>(nbNodes);
		Map<Integer, Subnet> subnets = new HashMap<Integer, Subnet>();
		
		//init connection counter
		for (int i = 0; i < nbNodes; i++) {
		    nbConnections.add(i, new AtomicInteger(0));
		}
		
		//init Links
		for (int i = 0; i < nbLinks; i++) {
			int nodeA = in.nextInt();
			int nodeB = in.nextInt();
			links.add(player.new Link(nodeA, nodeB));
			nbConnections.get(nodeA).incrementAndGet();
			nbConnections.get(nodeB).incrementAndGet();
		}
 
        //init gateways
		for (int j = 0; j < nbExits; j++) {
		    int gate = in.nextInt();
			gateways.add(gate);
			subnets.put(gate, player.new Subnet(gate));
		}
		
		//init subnets
		for(Link link : links){
		    if(gateways.contains(link.getNodeA()) && nbConnections.get(link.getNodeB()).intValue() == 3){
		        subnets.get(link.getNodeA()).addNode(link.getNodeB());
		    }
		    else if(gateways.contains(link.getNodeB()) && nbConnections.get(link.getNodeA()).intValue() == 3){
		        subnets.get(link.getNodeB()).addNode(link.getNodeA());
		    }
		}
		
		while (true) {
			// Read information from standard input
			int agentNode = in.nextInt();
 
			// Compute logic here
			if (!player.blockDirectLinkToGate(agentNode, gateways, links, nbConnections)) {
				// Block subnets
				Subnet subnetToBlock = null;
				int nbMax = 0;
				for(Map.Entry<Integer, Subnet> subnetEntry : subnets.entrySet()){
				    if(subnetEntry.getValue().getStatus() == 1){
				        subnetToBlock = subnetEntry.getValue();
				        break;
				    }
				    else if(subnetEntry.getValue().getStatus() == 2 && subnetEntry.getValue().getNbNodes() > nbMax){
				        subnetToBlock = subnetEntry.getValue();
				        nbMax = subnetEntry.getValue().getNbNodes();
				    }
				}
				if(subnetToBlock != null && subnetToBlock.getNbNodes() > 0){
				    Link linkToBlock = subnetToBlock.getLink(links, gateways);
    				linkToBlock.severs();
    				links.remove(links.indexOf(linkToBlock));
    				nbConnections.get(linkToBlock.getNodeA()).decrementAndGet();
    				nbConnections.get(linkToBlock.getNodeB()).decrementAndGet();
				}
				else{
				    links.get(0).severs();
				    links.remove(0);
				}
			}
		}
	}
}
