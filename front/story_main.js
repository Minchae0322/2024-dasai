// $('.select li').removeClass('active')
// $('.select li').click(function(){
//   $('.box').hide()
//   $(this).addClass('active')
//   $(this).siblings().removeClass('active')
//   $('.tabs > div').removeClass('active')
//   var tab = $(this).attr('data-alt')
//   $('#' + tab).addClass('active')  
// })

$(document).ready(function() {
  // 탭 클릭 이벤트 처리
  $('.select li').click(function() {
    $('.box').hide();
    $(this).addClass('active').siblings().removeClass('active');
    var tabId = $(this).attr('data-alt');
    $('.tabs > div').removeClass('active');
    $('#' + tabId).addClass('active');

    // 데이터 가져오기 함수 호출
    // fetchStoryData(tabId);
  });

  fetchStoryData();

  // 스토리 데이터를 백엔드에서 가져오는 함수
  function fetchStoryData() {
    console.log("fetchStoryData 함수 실행 시작"); //함수 실행 확인
    $.ajax({
      url: `http://15.164.230.127:8080/story/8/kr`, // 백엔드 엔드포인트 예시입니다. 실제 엔드포인트에 맞게 수정하세요.
      type: 'GET',
      dataType: 'json',
      success: function(data) {
        console.log("data received", data); // 데이터가 잘 받아졌는지 확인
      },
      error: function(error) {
        console.error('Error fetching story data:', error);
      }
    });
  }

  // 가져온 스토리 데이터를 화면에 순차적으로 표시하는 함수
  function displayStory(data, tabId) {
    var tabContent = $('#' + tabId);
    tabContent.empty(); // 기존 내용을 비웁니다.
  
    let delay = 0; // 초기 딜레이 시간 설정

    data.storyContents.forEach((content, index) => {
      // 메시지 딜레이 적용
      setTimeout(() => {
        const message = $('<p>').addClass('wen').text(content); // 'wen' 클래스 사용
        tabContent.append(message);

        $('html, body').animate({ scrollTop: $(document).height() }, 'slow');
      }, delay);

      delay += 1000; // 각 메시지 사이에 1초 딜레이 적용

      if (data.storyImagesUrl && data.storyImagesUrl[index]) {
        setTimeout(() => {
          const img = $('<img>').attr('src', data.storyImagesUrl[index]).css({
            maxWidth: '100%',
            borderRadius: '10px',
            marginTop: '5px'
          });
          tabContent.append(img);

          $('html, body').animate({ scrollTop: $(document).height() }, 'slow');
        }, delay);

        // 이미지 표시 후 딜레이 조정
        delay += 2000; // 다음 항목을 위한 추가 딜레이
      }
    });
  }
});