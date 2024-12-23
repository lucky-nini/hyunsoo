import java.util.*;
class Solution {
    static String[] userIds;
    static String[] bannedIds;
    static Set<Set<Integer>> results = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        userIds = user_id;
        bannedIds = banned_id;
        
        int answer = 0;
        
        boolean[] visited = new boolean[user_id.length];
        Set<Integer> result = new HashSet<>();
        dfs(0, visited, result); // idx, visited
        
        return results.size();
    }
    
    public void dfs(int idx, boolean[] visited, Set<Integer> result) {
        if (idx == bannedIds.length) {
            results.add(new HashSet<>(result));
            return;
        }
        
        for (int u = 0; u < userIds.length; u++) {
            if (!visited[u] && check(bannedIds[idx], userIds[u])) {
                visited[u] = true;
                result.add(u);
                dfs(idx + 1, visited, result);
                result.remove(u);
                visited[u] = false;
            }
        }
    }
    
    public static boolean check(String banId, String userId) {
        if (banId.length() != userId.length()) return false;
        
        for (int i = 0; i < banId.length(); i++) {
            if (banId.charAt(i) == '*') continue;
            else if (banId.charAt(i) != userId.charAt(i)) return false;
        }
        
        return true;
    }
}