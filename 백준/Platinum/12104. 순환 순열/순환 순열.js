const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function computeLPS(pattern) {
  const lps = new Array(pattern.length).fill(0);
  let j = 0;

  for (let i = 1; i < pattern.length; i++) {
    while (j > 0 && pattern[j] !== pattern[i]) {
      j = lps[j - 1];
    }

    if (pattern[j] === pattern[i]) {
      j++;
      lps[i] = j;
    }
  }

  return lps;
}

function kmpSearch(text, pattern) {
  const lps = computeLPS(pattern);
  let j = 0;
  const position = [];

  for (let i = 0; i < text.length; i++) {
    while (j > 0 && text[i] !== pattern[j]) {
      j = lps[j - 1];
    }
    if (text[i] === pattern[j]) {
      if (j === pattern.length - 1) {
        position.push(i - pattern.length + 1);
        j = lps[j];
      } else {
        j++;
      }
    }
  }

  return position;
}

function solution(arr) {
  const A = arr[0].trim();
  const B = arr[1].trim();

  const text = B + B.slice(0, B.length - 1);

  const position = kmpSearch(text, A);
  return position.length;
}

console.log(solution(input));