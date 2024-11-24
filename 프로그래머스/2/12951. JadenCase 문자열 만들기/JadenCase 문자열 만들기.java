class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();

        String[] words = s.split(" ", -1); // 마지막 공백도 유지하도록 split 사용
        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            if (!word.isEmpty()) {
                word = Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            }
            answer.append(word);
            if (w != words.length - 1) {
                answer.append(" "); 
            }
        }

        return answer.toString();
    }
}
