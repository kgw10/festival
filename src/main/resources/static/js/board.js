/* board.js */
document.addEventListener("DOMContentLoaded",function(){
    document.querySelectorAll(".post-link").forEach(post=>{
        post.addEventListener("click",function(event){
            event.preventDefault();
            openModal("chatroom");
        });
    });
    document.getElementById("createbt").addEventListener("click",function(){
        openModal("createroom");
    });
    document.getElementById("createroombt").addEventListener("click",function(){
        const title=document.getElementById("roomTitle").value;
        if(title.trim()){
            closeModal("createroom");
            openModal("chatroom");
        }else{
            alert("제목을 입력해주세요");
        }
    });
    document.getElementById("roomTitle").addEventListener("keypress",function(event){
        if(event.key=="Enter"){
            document.getElementById("createroombt").click();
        }
    });
    document.getElementById("joinbt").addEventListener("click",function(){
        const button=this;
        const isJoined=button.dataset.action=="leave";
        const boardId=button.dataset.boardId;
        if(isJoined){
            button.innerText="참가하기";
            button.classList.remove("joined");
            button.dataset.action="join";
        }else{
            button.innerText="참가취소";
            button.classList.add("joined");
            button.dataset.action="leave";
        }
    });
    document.getElementById("sendMessage").addEventListener("click",function(){
        const chatInput=document.getElementById("chatInput");
        const message=chatInput.value.trim();
        if(message){
            addMessage(message,true);
            chatInput.value="";
        }
    });
    document.getElementById("chatInput").addEventListener("keypress",function(event){
        if(event.key=="enter"){
            document.getElementById("sendMessage").click();
        }
    });
});
function openModal(modalId){
    document.getElementById(modalId).style.display="block";
}
function closeModal(modalId){
    document.getElementById(modalId).style.display="none";
}
//document.querySelector("#joinbt").addEventListener("click",async function(){
//    const board_id=this.dataset.board_id;
//    try{
//        const response=await fetch(`/party/join/${board_id}`,{method:'POST'});
//        if(response.ok){
//            this.innerText="참가취소";
//            this.classList.add("joined");
//            this.dataset.action="leave";
//        }
//    }catch(error){
//        console.error("참가 요청 중 오류 발생 :",error);
//    }
//});
function addMessage(message, isMyMessage){
    const chatArea=document.querySelector('.chat-area');
    const messageElement=document.createElement('div');
    messageElement.className=isMyMessage ? 'my-message' : 'other-message';
    messageElement.textContent=message;
    chatArea.appendChild(messageElement);
    chatArea.scrollTop=chatArea.scrollHeight;
}
