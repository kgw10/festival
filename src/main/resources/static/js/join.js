
// 이용약관 동의 시 회원가입 입력 폼 표시
function showSignUpForm() {
    const agreeCheckbox = document.getElementById("agreeTerms");
    const signUpForm = document.querySelector(".signUpForm");
    const formData = document.querySelector(".formData");

    // 약관에 동의하면 회원가입 입력 폼을 표시하고 약관 부분을 숨깁니다.
    if (agreeCheckbox && agreeCheckbox.checked) {
        if (signUpForm) signUpForm.style.display = "block";
        if (formData) formData.style.display = "none";
    } else {
        alert("이용약관에 동의해야 다음으로 진행할 수 있습니다.");
    }
}
function checkDuplicateId() {
    const userIdField = document.querySelector("input[name='id']"); // 아이디 입력란
    const idCheckResult = document.getElementById('idCheckResult'); // 결과를 표시할 요소

    if (userIdField) {
        const userId = userIdField.value.trim(); // trim()으로 공백 제거
        if (!userId) {
            alert("아이디를 입력하세요.");
            return;
        }

        // 서버로 중복 확인 요청
        fetch(`/member/checkDuplicateId?id=${encodeURIComponent(userId)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("서버 응답에 문제가 있습니다.");
                }
                return response.json(); // JSON 형식으로 변환
            })
            .then(data => {
                console.log(data.isDuplicate); // 콘솔에 중복 여부 출력

                if (data.isDuplicate) {
                    alert("이미 사용 중인 아이디입니다.");
                } else {
                    alert("사용 가능한 아이디입니다.")
                }
            })
            .catch(error => {
                console.error("Error:", error);
                alert("중복 확인 중 오류가 발생했습니다.");
            });
    }
}
    document.querySelector('form').addEventListener('submit', function() {
        var email = document.getElementById('email').value;
        var emailDomain = document.getElementById('emailDomain').value;

        // 이메일 앞부분과 도메인을 합쳐서 email 필드에 다시 설정
        document.getElementById('email').value = email + "@" + emailDomain;
    });

// 아이디 중복 확인
//function checkDuplicateId() {
//    const userIdField = document.querySelector("input[name='id']"); // 아이디 입력란
//    const idCheckResult = document.getElementById('idCheckResult'); // 결과를 표시할 요소
//
//    if (userIdField) {
//        const userId = userIdField.value.trim(); // trim()으로 공백 제거
//        if (!userId) {
//            alert("아이디를 입력하세요.");
//            return;
//        }
//
//        // 서버로 중복 확인 요청
//        fetch(`/member/checkDuplicateId?id=${userId}`)
//            .then(response => response.json())
//            .then(data => {
//                console.log(data.isDuplicate);
//                if (data.isDuplicate) {
//
//                    alert("이미 사용 중인 아이디입니다.");  // 중복 닉네임일 경우 알림창
//
//                } else {
//                    alert("사용 가능한 아이디입니다.");  // 사용 가능한 닉네임일 경우 알림창
//                }
//            })
//            .catch(error => console.error("Error:", error));
//    }
//}

// 닉네임 중복 확인
function checkDuplicateNickname() {
    const nicknameField = document.querySelector("input[name='nickname']"); // 닉네임 입력란
    const nicknameCheckResult = document.getElementById('nicknameCheckResult'); // 결과를 표시할 요소

    if (nicknameField) {
        const nickname = nicknameField.value.trim(); // trim()으로 공백 제거
        if (!nickname) {
            alert("닉네임을 입력하세요.");
            return;
        }

        // 서버로 중복 확인 요청
        fetch(`/member/checkDuplicateNickname?nickname=${nickname}`)
            .then(response => response.json())
            .then(data => {
                if (data.isDuplicate) {
                    alert("이미 사용 중인 닉네임입니다.");  // 중복 닉네임일 경우 알림창
                } else {
                    alert("사용 가능한 닉네임입니다.");  // 사용 가능한 닉네임일 경우 알림창
                }
            })
            .catch(error => console.error("Error:", error));
    }
}
