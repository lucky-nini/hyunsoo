const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const N = Number(arr[0]);
  let result = "";
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < r; c++) {
      result += " ";
    }
    for (let c = r; c < N; c++) {
      result += "*";
    }
    result += "\n";
  }
  return result;
}

console.log(solution(input));