<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}
</style>
</head>
<body>

	<h2>Ajax Test Page</h2>
	
	<div>
		<div>
			작성자 : <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			댓글 : <input type='text' name='replytext' id='newReplyText'>
		</div>
		<button id='replybtn'>댓글 추가</button>
	</div>
	
	<ul id="replies"></ul>
	<ul class='pagination'></ul>
	
	<div id='modDiv' style='display: none;'>
		<div class='modal-title'></div>
			<div>
				<input type='text' id='replytext'>
			</div>
			<div>
				<button type='button' id=replyModBtn>수정</button>
				<button type='button' id=replyDelBtn>삭제</button>
				<button type='button' id=closeBtn>닫기</button>
			</div>
	</div>

</body>
<script>

	var bno = 1622;
	var replyPage = 1;
	
	// 댓글 출력 함수
	function getAllList() {
		$.getJSON("/crud/replies/all/" + bno, function(data) {
		
			var str = "";
			console.log(data.length);
		
			$(data).each(
					function() {
						str += "<li data-rno='"+this.rno+"' class='replyLi'>"
							+ this.rno + ":" + this.replytext
							+ "<button>수정</button></li>";
					});
			$("#replies").html(str);
		});
	}
	
	// 댓글 페이징 처리 함수
	function getPageList(page) {
		$.getJSON("/crud/replies/"+bno+"/"+page, function(data) {
			console.log(data.list.length);
			
			var str = "";
			$(data.list).each(function() {
				str += "<li data-rno='"+this.rno+"' class='replyLi'>"
					+ this.rno+":"+ this.replytext + 
					"<button>수정</button></li>"
			});
			$("#replies").html(str);
			printPaging(data.pageMaker);
		});
	}
	
	// 페이징 처리
	function printPaging(pageMaker) {
		var str = "";
		
		if(pageMaker.prev) {
			str += "<li><a href='"+(pageMaker.startPage-1)+"'> << </a></li>";
		}
		
		for (var i=pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
			var strClass = pageMaker.cri.page == i?'class=active':'';
			str += "<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
		}
		
		if(pageMaker.next) {
			str += "<li><a href='"+(pageMaker.endPage+1)+"'> >> </a></li>";
		}
		$('.pagination').html(str);
	}
	
	// 페이지 번호 이벤트 처리
	$('.pagination').on('click', 'li a', function(event) {
		event.preventDefault();
		replyPage = $(this).attr("href");
		getPageList(replyPage);
	});
	
	// 댓글 추가 버튼
	$('#replybtn').on('click', function() {
		var replyer = $('#newReplyWriter').val();
		var replytext = $('#newReplyText').val();
		
		$.ajax({
			type : 'post',
			url : '/crud/replies',
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'POST'
			},
			dataType : 'text',
			data : JSON.stringify({
				bno : bno,
				replyer : replyer,
				replytext : replytext
			}),
			success : function(result) {
				if (result == 'success') {
					alert('등록 되었습니다.');
					getPageList(1);
					$('#newReplyWriter').val('');
					$('#newReplyText').val('');
				}
			}
		});
	});
	
	// 수정버튼
	$('#replies').on('click', '.replyLi button', function() {
		var reply = $(this).parent();
		
		var rno = reply.attr('data-rno');
		var replytext = reply.text();
		
		//alert(rno + " : " + replytext);
		$('.modal-title').html(rno);
		$('#replytext').val(replytext);
		$('#modDiv').show('slow');
	});
	
	// 닫기버튼
	$('#closeBtn').on('click', function() {
		$('#modDiv').hide('slow');
	});
	
	// 삭제 처리
	$('#replyDelBtn').on('click', function() {
		var rno = $('.modal-title').html();
		var replytext = $('#replytext').val();
		
		$.ajax({
			type : 'delete',
			url : '/crud/replies/' + rno,
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'DELETE'
			},
			dataType : 'text',
			success : function(result) {
				console.log("result : " + result);
				if (result == 'success') {
					alert("삭제 되었습니다.");
					$('#modDiv').hide('slow');
					getPageList(replyPage);
				}
			}
		});
	});
	
	// 수정 처리
	$('#replyModBtn').on('click', function() {
		var rno = $('.modal-title').html();
		var replytext = $('#replytext').val();
		
		$.ajax({
			type : 'put',
			url : '/crud/replies/' + rno,
			headers : {
				'Content-Type' : 'application/json',
				'X-HTTP-Method-Override' : 'PUT'
			},
			data : JSON.stringify({
				replytext : replytext}),
			dataType : 'text',
			success : function(result) {
				console.log('result : ' + result);
				if(result == 'success') {
					alert('수정 되었습니다.');
					$('#modDiv').hide('slow');
					getPageList(replyPage);
				}
			}
		});
	});
	getPageList(1);
</script>
</html>