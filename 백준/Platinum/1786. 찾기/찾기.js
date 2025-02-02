const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().split("\n"); // 제출 시 경로 수정 필요

function computeLPS(pattern) {
  const lps = new Array(pattern.length).fill(0);
  let j = 0; // 현재까지 일치한 접두사의 길이

  for (let i = 1; i < pattern.length; i++) {
    while (j > 0 && pattern[i] !== pattern[j]) {
      j = lps[j - 1]; // LPS 값 활용해 j를 이동 (접두사 길이를 줄여서 재검사)
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
  let position = [];

  for (let i = 0; i < text.length; i++) {
    while (j > 0 && text[i] !== pattern[j]) {
      j = lps[j - 1];
    }
    if (text[i] === pattern[j]) {
      j++;
      if (j === pattern.length) {
        position.push(i - j + 2); // 1-based index로 맞추기 위해 +1
        j = lps[j - 1]; // 다음 탐색을 위해 j 조정
      }
    }
  }
  return position;
}

function solution(arr) {
  const T = arr[0]; // 입력 텍스트 (공백 포함)
  const P = arr[1]; // 패턴 (공백 포함)

  const position = kmpSearch(T, P);

  let answer = position.length + "\n" + position.join(" "); // 개수 + 위치 출력
  return answer.trim();
}

// 실행
console.log(solution(input));