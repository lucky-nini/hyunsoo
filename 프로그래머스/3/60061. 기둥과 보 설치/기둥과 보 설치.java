import java.util.*;

class Solution {
    public static class Construction {
        int x; int y;
        Construction(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "["+x+", "+y+"]";
        }
    }
    public static class Constructions {
        boolean[][] pillars;
        boolean[][] ceilings;
        List<Construction> pillarList;
        List<Construction> ceilingList;
        Constructions(int n) {
            pillars = new boolean[n+1][n+1];
            ceilings = new boolean[n+1][n+1];
            pillarList = new ArrayList<>();
            ceilingList = new ArrayList<>();
        }
        
        boolean safeConst() {
            for (Construction c : pillarList) {
                boolean ableP = false;
                if (c.y == 0) ableP = true;
                else if ((c.x > 0 && ceilings[c.x - 1][c.y]) || ceilings[c.x][c.y]) ableP = true;
                else if (c.y > 0 && pillars[c.x][c.y - 1]) ableP = true;
                if (!ableP) return false;
            }
            for (Construction c : ceilingList) {
                boolean ableP = false;
                if ((c.y > 0 && pillars[c.x][c.y - 1]) || (c.x < pillars.length - 1 && pillars[c.x + 1][c.y - 1])) ableP = true;
                else if (c.x > 0 && c.x < ceilings.length - 1 && ceilings[c.x - 1][c.y] && ceilings[c.x + 1][c.y]) ableP = true;
                if (!ableP) return false;
            }
            return true;
        }
        
        void addPillar(int x, int y) {
            Construction newP = new Construction(x, y);
            this.pillarList.add(newP);
            pillars[x][y] = true;
            if (!safeConst()) {
                this.pillarList.remove(newP);
                pillars[x][y] = false;
            }
        }
        
        void addCeiling(int x, int y) {
            Construction newP = new Construction(x, y);
            this.ceilingList.add(newP);
            ceilings[x][y] = true;
            if (!safeConst()) {
                this.ceilingList.remove(newP);
                ceilings[x][y] = false;
            }
        }
        
        void removePillar(int x, int y) {
            Construction removeP = new Construction(0, 0);
            for (Construction c : pillarList) {
                if (c.x == x && c.y == y) {
                    removeP = c;
                    break;
                }
            }
            pillarList.remove(removeP);
            pillars[removeP.x][removeP.y] = false;
            if (!safeConst()) {
                this.pillarList.add(removeP);
                pillars[removeP.x][removeP.y] = true;
            }
        }
        
        void removeCeiling(int x, int y) {
            Construction removeP=new Construction(0, 0);
            for (Construction c : ceilingList) {
                if (c.x == x && c.y == y) {
                    removeP = c;
                    break;
                }
            }
            ceilingList.remove(removeP);
            ceilings[removeP.x][removeP.y] = false;
            if (!safeConst()) {
                this.ceilingList.add(removeP);
                ceilings[removeP.x][removeP.y] = true;
            }
        }
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        Constructions cons = new Constructions(n);
        
        for (int i=0; i<build_frame.length; i++) {
            if (build_frame[i][2]==0 && build_frame[i][3]==1) {
                cons.addPillar(build_frame[i][0], build_frame[i][1]);
            } else if (build_frame[i][2]==0 && build_frame[i][3]==0) {
                cons.removePillar(build_frame[i][0], build_frame[i][1]);
            } else if (build_frame[i][2]==1 && build_frame[i][3]==1) {
                cons.addCeiling(build_frame[i][0], build_frame[i][1]);
            } else if (build_frame[i][2]==1 && build_frame[i][3]==0) {
                cons.removeCeiling(build_frame[i][0], build_frame[i][1]);
            }
        }
        
        int[][] returnArray = new int[cons.pillarList.size()+cons.ceilingList.size()][3];
        int idx =0;
        for (Construction c: cons.pillarList) {
            returnArray[idx][0] = c.x;
            returnArray[idx][1] = c.y;
            returnArray[idx++][2] = 0;
        }
        for (Construction c: cons.ceilingList) {
            returnArray[idx][0] = c.x;
            returnArray[idx][1] = c.y;
            returnArray[idx++][2] = 1;
        }
        
        Arrays.sort(returnArray, new Comparator<int[]>(){
            @Override
            public int compare(int[] iArr1, int[] iArr2) {
                if (iArr1[0]!=iArr2[0]) return iArr1[0]-iArr2[0];
                else if (iArr1[1]!=iArr2[1]) return iArr1[1]-iArr2[1];
                else return iArr1[2]-iArr2[2];
            }
        });
        
        return returnArray;
    }
}
