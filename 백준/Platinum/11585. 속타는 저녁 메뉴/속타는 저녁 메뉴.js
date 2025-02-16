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

  let j = 0; // 패턴 위치 추적 인덱스
  let position = [];

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

function gcd(a, b) {
  return b === 0 ? a : gcd(b, a % b);
}

function solution(arr) {
  const N = Number(arr[0]);
  const meat = arr[1].trim().split(" ");
  const roulette = arr[2].trim().split(" ");

  let rString = [...roulette].concat(roulette);
  rString.pop();

  const position = kmpSearch(rString, meat);

  const divisor = gcd(N, position.length);

  const a = position.length / divisor;
  const b = N / divisor;

  return a + "/" + b;
}

console.log(solution(input));