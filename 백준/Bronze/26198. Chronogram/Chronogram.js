const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  let answers = "";
  const T = Number(arr[0].trim());
  for (let i = 0; i < T; i++) {
    let answer = 0;
    const code = arr[i + 1].trim();

    // 문자열을 배열처럼 순회
    for (const el of code) {
      switch (el) {
        case "I":
          answer += 1;
          break;
        case "V":
          answer += 5;
          break;
        case "X":
          answer += 10;
          break;
        case "L":
          answer += 50;
          break;
        case "C":
          answer += 100;
          break;
        case "D":
          answer += 500;
          break;
        case "M":
          answer += 1000;
          break;
      }
    }
    answers += answer + "\n";
  }
  return answers.trim();
}

console.log(solution(input));