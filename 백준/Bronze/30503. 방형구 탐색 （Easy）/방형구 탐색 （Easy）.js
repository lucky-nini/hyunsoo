const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  let result = "";
  const N = Number(arr[0].trim());
  const flowerBed = arr[1].trim().split(" ").map(Number);

  const Q = Number(arr[2].trim());
  for (let tc = 0; tc < Q; tc++) {
    const instruction = arr[tc + 3].trim().split(" ").map(Number);
    if (instruction[0] === 1) {
      const l = instruction[1];
      const r = instruction[2];
      const k = instruction[3];

      let cnt = 0;
      for (let idx = l - 1; idx < r; idx++) {
        if (flowerBed[idx] === k) cnt++;
      }

      result += cnt + "\n";
    } else if (instruction[0] === 2) {
      const l = instruction[1];
      const r = instruction[2];

      for (let idx = l - 1; idx < r; idx++) {
        flowerBed[idx] = 0;
      }
    }
  }
  return result;
}

console.log(solution(input));