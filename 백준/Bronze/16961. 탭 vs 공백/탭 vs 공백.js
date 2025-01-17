const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

function solution(arr) {
  const N = Number(arr[0]);

  const tMap = new Map();
  const sMap = new Map();

  let day1 = 0; // 투숙객 1명 이상
  let day2 = 0; // 가장 많은 투숙객 있었던 날 투숙 인원
  let day3 = 0; // 싸움이 없는 날의 수
  let day4 = 0; // 싸움이 없는 날 가장 많은 투숙객이 있었던 날에 투숙한 사람의 수
  let day5 = 0; // 가장 오랜 기간 투숙한 사람이 투숙한 날의 수

  for (let i = 0; i < N; i++) {
    const info = arr[i + 1].split(" ");
    const c = info[0];
    const s = Number(info[1]);
    const e = Number(info[2]);
    // console.log(c, s, e);

    day5 = Math.max(day5, e - s + 1);

    if (c === "T") {
      for (let day = s; day <= e; day++) {
        tMap.set(day, !tMap.has(day) ? 1 : tMap.get(day) + 1);
      }
    } else {
      for (let day = s; day <= e; day++) {
        sMap.set(day, !sMap.has(day) ? 1 : sMap.get(day) + 1);
      }
    }
  }

  for (let day = 1; day <= 366; day++) {
    const tDay = tMap.has(day) ? tMap.get(day) : 0;
    const sDay = sMap.has(day) ? sMap.get(day) : 0;
    if (tDay + sDay >= 1) day1++;
    if (tDay + sDay > day2) day2 = tDay + sDay;
    if (tDay === sDay && tDay !== 0 && sDay !== 0) day3++;
    if (tDay === sDay && tDay !== 0 && sDay !== 0 && tDay + sDay > day4)
      day4 = tDay + sDay;
  }

  const result = day1 + "\n" + day2 + "\n" + day3 + "\n" + day4 + "\n" + day5;
  return result;
}

console.log(solution(input));