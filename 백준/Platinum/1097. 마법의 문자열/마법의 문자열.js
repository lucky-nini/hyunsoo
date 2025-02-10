const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

let cnt = 0;
let K;

function createLPS(pattern) {
  const lps = new Array(pattern.length).fill(0);
  let j = 0;

  for (let i = 1; i < pattern.length; i++) {
    while (j > 0 && pattern[i] !== pattern[j]) {
      j = lps[j - 1];
    }
    if (pattern[i] == pattern[j]) {
      j++;
      lps[i] = j;
    }
  }

  return lps;
}

function kmpSearch(text, pattern) {
  const lps = createLPS(pattern);
  let j = 0;
  const position = [];

  for (let i = 0; i < text.length; i++) {
    while (j > 0 && text[i] !== pattern[j]) {
      j = lps[j - 1];
    }
    if (text[i] === pattern[j]) {
      if (j === pattern.length - 1) {
        position.push(i - j);
        j = lps[j];
      } else {
        j++;
      }
    }
  }

  return position;
}

function permutate(arr, visited, selected) {
  if (selected.length === arr.length) {
    const pattern = selected.reduce((acc, el) => acc + el, "");
    const text = pattern + pattern.slice(0, pattern.length - 1);
    const position = kmpSearch(text, pattern);
    if (position.length === K) cnt++;
    return;
  }

  for (let i = 0; i < arr.length; i++) {
    if (!visited[i]) {
      visited[i] = true;
      selected.push(arr[i]);
      permutate(arr, visited, selected);
      selected.pop();
      visited[i] = false;
    }
  }
}

function solution(arr) {
  const N = Number(arr[0]);
  const words = arr.slice(1, N + 1).map((el) => el.trim());
  K = Number(arr[N + 1]);
  const visited = new Array(N).fill(false);
  const selected = [];
  permutate(words, visited, selected);
  return cnt;
}

console.log(solution(input));