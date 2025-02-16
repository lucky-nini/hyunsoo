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
  let [S, K] = arr[0].split(" ");
  K = Number(K);

  const lps = computeLPS(S);
  const N = S.length;
  return N + (K - 1) * (N - lps[N - 1]);
}

console.log(solution(input));