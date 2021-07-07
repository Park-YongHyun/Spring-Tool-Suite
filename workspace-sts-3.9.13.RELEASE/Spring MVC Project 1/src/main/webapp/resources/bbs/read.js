// 삭제 버튼
function deletePost (postNum) {
	const password = document.querySelector('#postPassword').value

	const xhr = new XMLHttpRequest()
	xhr.onload = () => {
		switch (xhr.responseText) {
		case 'success':
			alert('삭제 되었습니다.')
			document.querySelector('#gotoListButton').click()
			break
		case 'failure':
			alert('암호가 틀렸습니다.')
			break
		}
	}
	xhr.open('delete', `/bbs/delete?num=${postNum}&password=${password}`)
	xhr.send()
}
