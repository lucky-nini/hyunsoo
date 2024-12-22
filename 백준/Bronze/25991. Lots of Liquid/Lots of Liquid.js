const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const N = Number(arr[0].trim());

  const cubes = arr[1].trim().split(" ").map(Number);
  const volume = cubes.reduce((acc, cur) => acc + cur * cur * cur, 0);

  return Math.cbrt(volume);
}

console.log(solution(input));