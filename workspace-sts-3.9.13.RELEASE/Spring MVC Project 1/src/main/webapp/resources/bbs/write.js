// 내용 검사
document.querySelector('#postForm').addEventListener('submit', (event) => {
	if (document.querySelector('br[data-cke-filler="true"]')) {
		event.preventDefault()
		alert('내용을 입력하세요')
	}
})
