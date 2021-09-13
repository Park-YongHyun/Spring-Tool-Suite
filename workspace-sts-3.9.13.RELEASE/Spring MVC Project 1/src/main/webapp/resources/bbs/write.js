// 내용 검사
document.querySelector('#postForm').addEventListener('submit', (event) => {
	if (!editor.getData()) {
		event.preventDefault()
		alert('내용을 입력하세요')
	}
})
