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

function kmpSearch(text, pattern) {
  const lps = computeLPS(pattern);

  let j = 0;

  for (let i = 0; i < text.length; i++) {
    while (j > 0 && text[i] !== pattern[j]) {
      j = lps[j - 1];
    }
    if (text[i] === pattern[j]) {
      if (j === pattern.length - 1) {
        return true;
      } else {
        j++;
      }
    }
  }

  return false;
}

function solution(arr) {
  const [N, K] = arr[0].split(" ").map(Number);
  const programs = Array.from({ length: N }, () => new Array(0));
  for (let i = 1; i <= N; i++) {
    programs[i - 1].push(...arr[2 * i].split(" ").map(Number));
  }

  let yes = false;
  for (let i = 0; i < programs[0].length - K + 1; i++) {
    const pattern = programs[0].slice(i, i + K);
    let ok = true;
    for (let n = 1; n < N; n++) {
      if (
        !kmpSearch(programs[n], pattern) &&
        !kmpSearch(programs[n].reverse(), pattern)
      ) {
        ok = false;
      }
    }
    if (ok) {
      yes = true;
      return "YES";
    }
  }

  return yes ? "YES" : "NO";
}

console.log(solution(input));