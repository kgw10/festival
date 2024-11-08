/* diary.js */
let cd=new Date();
const fesdate=["2024-10-31","2024-11-01","2024-11-02","2024-11-03","2024-11-04","2024-11-05","2024-11-06","2024-11-07","2024-11-08","2024-11-09","2024-11-10","2024-11-11"]
function updateCalendar(){
    const year=cd.getFullYear();  // cd = currentDate
    const month=cd.getMonth();
    const daysInMonth=new Date(year, month+1, 0).getDate();
    document.getElementById("cym").textContent=`${year}.${(month+1).toString().padStart(2,'0')}`;
    const cb=document.getElementById("calendar");  // cb = calendarBody
    cb.innerHTML="";
    const startDay=new Date(year, month, 1).getDay();
    let day=1;
    let rowadd=0;
    let row=document.createElement("tr");
    for(let i=0;i<startDay;i++){
        let emptyCell=document.createElement("td");
        emptyCell.innerHTML="-";
        row.appendChild(emptyCell);
    }
    for(let i=startDay;i<7;i++){
        if(day>daysInMonth) break;
        let cell=createCalendarCell(day,year,month,i);
        row.appendChild(cell);
        day++;
    }
    cb.appendChild(row);
    rowadd++;
    while(day<=daysInMonth){
        row=document.createElement("tr");
        for(let i=0;i<7;i++){
            if(day>daysInMonth){
                let emptyCell=document.createElement("td");
                emptyCell.innerHTML="-";
                row.appendChild(emptyCell);
            }else{
                let cell=createCalendarCell(day,year,month,i);
                row.appendChild(cell);
                day++;
            }
        }
        cb.appendChild(row);
        rowadd++;
    }
    while(rowadd<6){
        row=document.createElement("tr");
        for(let i=0;i<7;i++){
            let emptyCell=document.createElement("td");
            emptyCell.innerHTML="-";
            row.appendChild(emptyCell);
        }
        cb.appendChild(row);
        rowadd++;
    }
}
function createCalendarCell(day,year,month,weekdayIndex){
    let cell=document.createElement("td");
    cell.className="calendar_day";
    cell.textContent=day;
    cell.setAttribute("data-dat",day);
    if(weekdayIndex==0)cell.classList.add("redday");
    if(weekdayIndex==6)cell.classList.add("blueday");
    const cellDate=`${year}-${(month+1).toString().padStart(2,'0')}-${day.toString().padStart(2,'0')}`;
    if(fesdate.includes(cellDate)){
        cell.style.backgroundColor="yellow";
        cell.style.cursor="pointer";
        cell.addEventListener("click",showFes);
    }else{
        cell.style.cursor="default";
        cell.addEventListener("click",hideFes);
    }
    return cell;
}
function prevMonth(){
    cd.setMonth(cd.getMonth()-1);
    updateCalendar();
}
function nextMonth(){
    cd.setMonth(cd.getMonth()+1);
    updateCalendar();
}
function showFes(){
    document.querySelector('#festival').style.display='block';
}
function hideFes(){
    document.querySelector('#festival').style.display='none';
}
window.onload=function(){
    updateCalendar();
}
function alarmToggle(){
    const alarm=document.querySelector("#alarm_bt i");
    if(alarm.classList.contains("bi-bell-slash")){
        alarm.classList.remove("bi-bell-slash");
        alarm.classList.add("bi-bell");
        alarm.setAttribute("aria-label","알람on");
    }else{
        alarm.classList.remove("bi-bell");
        alarm.classList.add("bi-bell-slash");
        alarm.setAttribute("aria-label","알람off");
    }
}
function delFes(){
    alert("축제 예약이 취소되었습니다.");
}
