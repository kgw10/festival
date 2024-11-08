/* search.js */
let displayLimit=9;
lit totalFestivals=0;
document.addEventListener("DOMContentLoaded",function(){
    loadFestivals();
    document.getElementById("load_more_bt").addEventListener("click",function(){
        displayLimit+=18;
        loadFestivals();
    });
});
function loadFestivals(){
    const festivals=JSON.parse(document.getElementById("festival_list").getAttribute("data-festivals"));
    totalFestivals=festivals.length;
    renderFestivals(festivals.slice(0,displayLimit));
    const loadMoreButton=document.getElementById("load_more_bt");
    if(displayLimit>=totalFestivals){
        loadMoreButton.style.backgroundColor="gray";
        loadMoreButton.disabled=true;
    }else{
        loadMoreButton.style.backgroundColor="orange";
        loadMoreButton.disabled=false;
    }
}
function renderFestivals(festivals){
    const festivalList=document.getElementById("festival_list");
    festivalList.innerHTML="";
    festivals.forEach((festival,index)=>{
        if(index%3==0){
            const rowDiv=document.createElement("div");
            rowDiv.classList.add("festival-row");
            festivalList.appendChild(rowDiv);
        }
        const festivalDiv=document.createElement("div");
        festivalDiv.className="day_festivals";
        festivalDiv.innerHTML=`
            <div class="fes_sinfo">
                <img class="fes_img" src="${festival.img_url}" alt="축제이미지">
                <div class="fes_name">${festival.festival_name}</div>
                <div class="fes_start_date">${festival.festival_start_date}</div>
                <div class="fes_end_date">${festival.festival_end_date}</div>
            </div>`;
        festivalList.lastChild.appendChild(festivalDiv);
    });
}
