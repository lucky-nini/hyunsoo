const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const [N, M] = arr[0].split(" ").map(Number);
  const spaces = [];
  for (let i = 1; i < arr.length; i++) {
    spaces.push(arr[i].split(" ").map(Number));
  }

  let cnt = 0;

  const isEqual = (aP, bP) => {
    let notEqual = false;
    for (let p = 0; p < M - 1; p++) {
      for (let np = 0; np < M; np++) {
        if (
          !(
            (aP[p] === aP[np] && bP[p] === bP[np]) ||
            (aP[p] < aP[np] && bP[p] < bP[np]) ||
            (aP[p] > aP[np] && bP[p] > bP[np])
          )
        ) {
          notEqual = true;
          break;
        }
      }
    }
    return !notEqual;
  };

  for (let s = 0; s < N - 1; s++) {
    for (let ns = s + 1; ns < N; ns++) {
      if (isEqual(spaces[s], spaces[ns])) cnt++;
    }
  }

  return cnt;
}

console.log(solution(input));