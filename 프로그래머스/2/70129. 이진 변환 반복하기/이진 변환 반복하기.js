function solution(s) {
    var answer = [];
    
    let removeZero = 0;
    let cnt = 0;
    
    while (s!=="1") {
        removeZero += s.length;
        s = s.split("").filter(el=>el!="0").join("").trim();
        removeZero-=s.length;
        s = s.length.toString(2);
        cnt++;
    }
    
    answer.push(cnt);
    answer.push(removeZero);
    
    return answer;
}