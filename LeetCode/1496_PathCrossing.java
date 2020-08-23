class Solution {
    public boolean isPathCrossing(String path) {
		Set<String> vis = new HashSet<String>();
        int x = 0; int y = 0;
        vis.add(x + "," + y);
        int length = path.length();
        for(int i = 0; i < length; i++){
            char dir = path.charAt(i);
            switch(dir){
                case 'N' : --x; break;
                case 'S' : ++x; break;
                case 'W' : --y; break;
                case 'E' : ++y; break;
            }
            
            if(vis.contains(x + "," + y)){
                return true;
            }
            else{
                vis.add(x + "," + y);
            }
        }
        return false;
    }   
}
