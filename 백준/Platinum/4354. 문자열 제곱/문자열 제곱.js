const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function computeLPS(pattern) {
  const lps = new Array(pattern.length).fill(0);
  let j = 0; // 일치한 접두사 길이

  for (let i = 1; i < pattern.length; i++) {
    while (j > 0 && pattern[i] !== pattern[j]) {
      j = lps[j - 1];
    }
    if (pattern[i] === pattern[j]) {
      j++;
      lps[i] = j;
    }
  }
  return lps;
}

function solution(arr) {
  let idx = 0;
  let answer = "";
  while (arr[idx] !== ".") {
    const s = arr[idx++].trim();
    const len = s.length - computeLPS(s)[s.length - 1];
    answer += s.length % len === 0 ? s.length / len : 1;
    answer += "\n";
  }
  return answer;
}

console.log(solution(input));