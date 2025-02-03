const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function computeLPS(pattern) {
  const lps = new Array(pattern.length).fill(0);
  let j = 0;

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
  const N = Number(arr[0]);
  const text = arr[1];
  const lps = computeLPS(text);
  return N - lps[N - 1];
}

console.log(solution(input));