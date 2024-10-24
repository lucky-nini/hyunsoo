class Solution {
    public static class Player {
        int curPos;
        int opStart, opEnd;
        int videoLen;
        Player(String pos, String op_start, String op_end, String video_len) {
            this.curPos = timeToSec(pos);
            this.opStart = timeToSec(op_start);
            this.opEnd = timeToSec(op_end);
            this.videoLen = timeToSec(video_len);
            skip();
        }
        
        int timeToSec(String time) {
            String[] minNSec = time.split(":");
            int min = Integer.parseInt(minNSec[0]);
            int sec = Integer.parseInt(minNSec[1]);
            return min*60 + sec;
        }
        
        String secToTime(int totSec) {
            String min = ""+totSec/60;
            String sec = ""+totSec%60;
            if (min.length()==1) min = "0"+min;
            if (sec.length()==1) sec = "0"+sec;
            return min+":"+sec;
        }
        
        void skip() {
            if (this.curPos>=this.opStart && this.curPos<=this.opEnd) this.curPos = this.opEnd;
        }
        void moveNext() {
            this.curPos = this.curPos+10<this.videoLen?this.curPos+10:this.videoLen;
            skip();
        }
        void movePrev() {
            this.curPos = this.curPos-10>0?this.curPos-10:0;
            skip();
        }
        
        String getPos() {
            return secToTime(this.curPos);
        }
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        
        Player p = new Player(pos, op_start, op_end, video_len);
        
        for (String c : commands) {
            if (c.equals("next")) {
                p.moveNext();
            } else if (c.equals("prev")) {
                p.movePrev();
            }
        }
        
        return p.getPos();
    }
}