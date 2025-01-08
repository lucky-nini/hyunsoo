const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const nums = arr[0].split(" ").map((n) => BigInt(n));
  return nums.reduce((acc, curr) => acc + curr, 0n).toString();
}

console.log(solution(input));