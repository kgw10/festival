/* festival.js */
let cd=new Date();
let selectedDate=null;
let displayLimit=9;  // 초기 표시 9개
let totalFestivals=0;
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
    cell.addEventListener("click", () => selectDate(day, year, month));  // 날짜 클릭 이벤트 추가
    return cell;
}
document.addEventListener("DOMContentLoaded",function(){
    document.querySelector(".prev").addEventListener("click",prevMonth);
    document.querySelector(".next").addEventListener("click",nextMonth);
    document.getElementById("load_more_bt").addEventListener("click",loadMoreFestivals);
//    document.querySelectorAll(".festival-card").forEach(card=>{
//        card.addEventListener("click",function(){
//            const festivalId=this.dataset.id;  // dataset 사용하여 축제정보id에 접근
//            window.location.href=`/festival/info/${festivalId}`;
//        });
//    });
    updateCalendar();
});
function prevMonth(){
    cd.setMonth(cd.getMonth()-1);
    updateCalendar();
}
function nextMonth(){
    cd.setMonth(cd.getMonth()+1);
    updateCalendar();
}
window.onload=function(){
    updateCalendar();
}
function loadMoreFestivals(){
    if(!selectedDate){
        console.error("날짜 미선택");
        return;
    }
    displayLimit+=6;
    loadFestivals(selectedDate);
}
function selectDate(day,year,month){
    selectedDate=`${year}-${String(month+1).padStart(2,'0')}-${String(day).padStart(2,'0')}`;
    document.querySelectorAll(".calendar_day").forEach(cell=>cell.style.backgroundColor="");
    document.querySelector(`[data-date="${day}"]`).style.backgroundColor="yellow";
    loadFestivals(selectedDate);
}
async function loadFestivals(date){
    if(!date){
        console.error("날짜미선택");
        return;
    }
    const formatDate=date.replace(/-/g,"");
    try{
        const response=await fetch(`/festival/data?date=${formatDate}`);
        if(!response.ok){
            console.error("DB에서 데이터를 가져오지 못했음");
            return;
        }
        const data=await response.json();
        totalFestivals=data.length;
        if(totalFestivals==0){ festivalList.innerHTML="<p>표시할 축제가 없습니다.</p>"; }  // 데이터 확인
        renderFestivals(data.slice(0,displayLimit));
        document.getElementById("load_more_bt").style.display=totalFestivals>displayLimit ? "block" : "none";
    }catch(error){
        console.error("DB 요청 중 오류 발생");
    }
}
function renderFestivals(festivals){
    const festivalList=document.getElementById("festival-list");
    festivalList.innerHTML="";  // 이전 내용 제거
    let rowDiv=null;
    festivals.forEach((festival,index)=>{
        if(index%3==0){
            rowDiv=document.createElement("div");
            rowDiv.className="festival-row";
            festivalList.appendChild(rowDiv);
        }
        const festivalDiv=document.createElement("div");
        festivalDiv.className="festival-card";
        festivalDiv.dataset.id=festival.festival_id;
        const imageUrl=festival.firstimage ? festival.firstimage : 'https://via.placeholder.com/300x180';
        festivalDiv.innerHTML=`
            <img class="fes_img" src="${imageUrl}" alt="축제 이미지">
            <div class="fes_name">${festival.title}</div>
            <div class="fes_start_date">시작일: ${festival.eventstartdate}</div>
            <div class="fes_end_date">종료일: ${festival.eventenddate}</div>
            <div class="fes_summary">장소: ${festival.addr1}</div>
        `;
        festivalDiv.addEventListener("click",function(){  // 클릭 시 이벤트
            window.location.href=`/festival/info/${festival.festival_id}`;
        });
        if(rowDiv){ rowDiv.appendChild(festivalDiv); }
    });
}
//function renderFestivals(festivals){
//    const festivalList=document.getElementById("festival-list");
//    festivalList.innerHTML="";  // 이전 내용 제거
//    festivals.forEach(festival=>{
//        const festivalDiv=document.createElement("div");
//        festivalDiv.className="fes_sinfo";
//        festivalDiv.innerHTML=`
//            <img class="fes_img" src="${festival.firstimage}" alt="축제 이미지">
//            <div class="fes_name">${festival.title}</div>
//            <div class="fes_start_date">${festival.eventstartdate}</div>
//            <div class="fes_end_date">${festival.eventenddate}</div>
//            <div class="fes_summary">${festival.addr1}</div>
//        `;
//        festivalList.appendChild(festivalDiv);
//    });
//}
