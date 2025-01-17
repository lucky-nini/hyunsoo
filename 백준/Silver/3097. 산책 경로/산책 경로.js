const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const N = Number(arr[0]);
  const dots = [];
  for (let i = 0; i < N; i++) {
    dots.push(arr[i + 1].split(" ").map(Number));
  }

  function connectDots(start, next) {
    let x = start[0];
    let y = start[1];
    x += next[0];
    y += next[1];
    return [x, y];
  }

  // 그냥 이으면
  let curDot = [0, 0];
  for (let i = 0; i < N; i++) {
    curDot = connectDots(curDot, dots[i]);
  }

  let minLen = 2000;
  for (let l = 0; l < N; l++) {
    let dot = [0, 0];
    for (let i = 0; i < N; i++) {
      if (i != l) {
        dot = connectDots(dot, dots[i]);
      }
    }
    minLen = Math.min(
      Math.sqrt(
        Math.pow(Math.abs(dot[0]), 2) + Math.pow(Math.abs(dot[1], 2), 2)
      ),
      minLen
    );
  }

  let result = curDot[0] + " " + curDot[1] + "\n";
  result += minLen.toFixed(2);

  return result;
}

console.log(solution(input));