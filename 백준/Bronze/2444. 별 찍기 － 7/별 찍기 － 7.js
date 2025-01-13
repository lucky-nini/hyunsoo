const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const N = Number(arr[0]);
  // n번째 줄에는 2*n -1 개의 별
  // 총 길이는 2*N-1
  // n번째 줄의 각 공백은 (2*N-1-2*n+1)/2 = N-n
  let result = "";
  for (let r = 1; r <= N; r++) {
    for (let c = 0; c < N - r; c++) {
      result += " ";
    }
    for (let c = 0; c < 2 * r - 1; c++) {
      result += "*";
    }
    result += "\n";
  }

  for (let r = N - 1; r >= 1; r--) {
    for (let c = 0; c < N - r; c++) {
      result += " ";
    }
    for (let c = 0; c < 2 * r - 1; c++) {
      result += "*";
    }
    result += "\n";
  }

  return result;
}

console.log(solution(input));