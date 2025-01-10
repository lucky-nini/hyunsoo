const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  let idx = 0;
  let result = "";
  for (let tc = 0; tc < 3; tc++) {
    const N = Number(arr[idx]);
    let sum = 0n;
    for (let i = 0; i < N; i++) {
      sum += BigInt(arr[++idx]);
    }
    idx++;
    result += sum === 0n ? 0 : sum > 0 ? "+" : "-";
    result += "\n";
  }
  return result;
}

console.log(solution(input));