const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const N = Number(arr[0]);
  let result = "";
  for (let r = N; r >= 1; r--) {
    for (let c = 1; c <= N - r; c++) {
      result += " ";
    }
    for (let c = 1; c <= 2 * r - 1; c++) {
      result += "*";
    }
    result += "\n";
  }
  return result;
}

console.log(solution(input));