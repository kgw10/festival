/* default.js */
document.addEventListener("DOMContentLoaded",function(){
    console.log("메인화면 로딩");
});
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
