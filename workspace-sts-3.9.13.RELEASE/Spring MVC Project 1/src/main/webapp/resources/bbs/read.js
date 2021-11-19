if (document.readyState === 'loading') {
	document.addEventListener('DOMContentLoaded', firstToDo)
} else {
	firstToDo()
}

let commentPageSize = 20
let commentPageNum = 1
function firstToDo () {
	readCommentList(commentPageNum, commentPageSize)
}

// 게시글 삭제 버튼
function deletePost (postNum) {
	const postDeleteModal = document.querySelector('#postDeleteModal')

	const xhr = new XMLHttpRequest()
	xhr.addEventListener('load', () => {
		if (xhr.status === 200) {
			document.querySelector('#goto-list-button').click()
		} else {
			alert('암호가 틀렸습니다.')
		}
	})
	xhr.open('delete', '/bbs/delete', true)
	xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
	xhr.send(JSON.stringify({
		num: postNum,
		password: postDeleteModal.querySelector('.data-password').value
	}))
}

// 댓글 or 대댓글 작성
function writeComment (parent) {
	const commentWriteCard = parent ? document.querySelector('#inserted-comment-write-card') : document.querySelector('#comment-write-card')
	const currentUrlParams = new URLSearchParams(location.search)
	const writeCommentParams = {
		// postNum, content, writer, password, parent
		postNum: currentUrlParams.get('num'),
		content: commentWriteCard.querySelector('.data-content').value,
		writer: commentWriteCard.querySelector('.data-writer').value,
		password: commentWriteCard.querySelector('.data-password').value,
		parent: parent || 0
	}

	commentService.writeComment(writeCommentParams, (result) => {
		commentWriteCard.querySelector('.data-content').value = ''
		readCommentList(1, commentPageSize)
	})
}

// 댓글 목록, 페이지 버튼
function readCommentList (pageNum, pageSize) {
	const currentUrlParams = new URLSearchParams(location.search)
	const readCommentListParams = {
		postNum: currentUrlParams.get('num'),
		pageNum: pageNum,
		pageSize: pageSize
	}

	commentService.readCommentList(readCommentListParams, (result) => {
		const commentList = document.querySelector('#comment-list')
		commentList.innerHTML = ''
		const commentCardTemplate = document.querySelector('#comment-template').content.querySelector('.card')
		JSON.parse(result.responseText).commentList.forEach(comment => {
			const commentCard = commentCardTemplate.cloneNode(true)
			if (comment.depth) {
				commentCard.style.marginLeft = `${comment.depth * 2}rem`
			}
			commentCard.dataset.commentNum = comment.num
			commentCard.querySelector('.comment-writer').innerText = comment.writer
			commentCard.querySelector('.comment-content').innerText = comment.content
			commentCard.querySelector('.comment-write-date').innerText = comment.writeDate ? `작성: ${convertDate(comment.writeDate)}` : ''
			commentCard.querySelector('.comment-update-date').innerText = comment.updateDate ? `수정: ${convertDate(comment.updateDate)}` : ''

			// 댓글 카드의 답글 버튼
			commentCard.querySelector('.comment-reply-button').addEventListener('click', () => {
				const oldCard = document.querySelector('#inserted-comment-write-card')
				if (oldCard) {
					oldCard.remove()
				}
				const commentWriteCard = document.querySelector('#comment-write-card').cloneNode(true)
				commentWriteCard.id = 'inserted-comment-write-card'
				commentWriteCard.style.marginLeft = `${(comment.depth + 1) * 2}rem`

				commentWriteCard.querySelector('.submit-button').setAttribute('onclick', `${writeComment.name}(${comment.num})`)
				commentWriteCard.querySelector('.btn-close').style.removeProperty('display')
				commentWriteCard.querySelector('.btn-close').addEventListener('click', () => commentWriteCard.remove())

				document.querySelector(`div[data-comment-num="${comment.num}"]`).after(commentWriteCard)
			})

			// 댓글 카드의 수정 버튼
			commentCard.querySelector('.comment-edit-button').addEventListener('click', () => {
				const oldCard = document.querySelector('#inserted-comment-write-card')
				if (oldCard) {
					oldCard.remove()
				}
				const commentWriteCard = document.querySelector('#comment-write-card').cloneNode(true)
				commentWriteCard.id = 'inserted-comment-write-card'
				commentWriteCard.style.marginLeft = `${comment.depth * 2}rem`

				commentWriteCard.querySelector('.data-content').value = comment.content
				commentWriteCard.querySelector('.data-writer').value = comment.writer
				commentWriteCard.querySelector('.data-writer').setAttribute('readonly', '')

				commentWriteCard.querySelector('.submit-button').setAttribute('onclick', `${editComment.name}(${comment.num})`)
				commentWriteCard.querySelector('.btn-close').style.removeProperty('display')
				commentWriteCard.querySelector('.btn-close').addEventListener('click', () => commentWriteCard.remove())

				document.querySelector(`div[data-comment-num="${comment.num}"]`).after(commentWriteCard)
			})

			// 댓글 카드의 삭제 버튼
			commentCard.querySelector('.comment-delete-button').addEventListener('click', () => {
				const oldCard = document.querySelector('#inserted-comment-write-card')
				if (oldCard) {
					oldCard.remove()
				}
				document.querySelector('#commentDeleteModal button.submit-button').setAttribute('onclick', `${deleteComment.name}(${comment.num})`)
			})

			commentList.appendChild(commentCard)
		})

		// 댓글 페이지 버튼
		const page = JSON.parse(result.responseText).page
		const pageButtonsArrow = document.querySelectorAll('#page-button-group li.page-button-arrow > a')
		const pageButtonsNumber = document.querySelectorAll('#page-button-group li.page-button-number > a')
		pageButtonsArrow.forEach(button => {
			button.style.display = 'none'
		})
		pageButtonsNumber.forEach(button => {
			button.style.display = 'none'
		})
		function setPageButton (button, pageNum) {
			button.style.removeProperty('display')
			button.onclick = () => {
				commentPageNum = pageNum
				readCommentList(commentPageNum, commentPageSize)
			}
		}
		if (page.prev) {
			setPageButton(pageButtonsArrow[0], 1)
			setPageButton(pageButtonsArrow[1], page.startPage - 1)
		}
		if (page.next) {
			setPageButton(pageButtonsArrow[2], page.endPage + 1)
			setPageButton(pageButtonsArrow[3], page.totalPage)
		}
		for (let index = 0, pageNumber = page.startPage; pageNumber <= page.endPage; index++, pageNumber++) {
			const button = pageButtonsNumber[index]
			button.innerText = pageNumber
			setPageButton(button, pageNumber)

			if (page.pageNum === pageNumber) {
				button.parentElement.classList.add('active')
			} else {
				button.parentElement.classList.remove('active')
			}
		}
	})

	function convertDate (date) {
		let d = new Date(date)
		d = {
			year: d.getFullYear(),
			month: `${d.getMonth() + 1}`.padStart(2, '0'), // ! getMonth()는 0부터 시작
			date: `${d.getDate()}`.padStart(2, '0'),
			hours: `${d.getHours()}`.padStart(2, '0'),
			minutes: `${d.getMinutes()}`.padStart(2, '0'),
			seconds: `${d.getSeconds()}`.padStart(2, '0')
		}
		if (date > Date.now() - 1000 * 3600 * 24 * 30) {
			return `${d.year}.${d.month}.${d.date} ${d.hours}:${d.minutes}:${d.seconds}`
		} else {
			return `${d.hours}:${d.minutes}:${d.seconds}`
		}
	}
}

// 댓글 수정
function editComment (num) {
	const commentWriteCard = document.querySelector('#inserted-comment-write-card')
	const editCommentParams = {
		// num, content, password
		num: num,
		content: commentWriteCard.querySelector('.data-content').value,
		password: commentWriteCard.querySelector('.data-password').value
	}

	commentService.editComment(editCommentParams, (result) => {
		readCommentList(commentPageNum, commentPageSize)
	})
}

// 댓글 삭제
function deleteComment (num) {
	const modal = document.querySelector('#commentDeleteModal')
	const deleteCommentParams = {
		// num, password
		num: num,
		password: modal.querySelector('.data-password').value
	}
	commentService.deleteComment(deleteCommentParams, (result) => {
		readCommentList(commentPageNum, commentPageSize)
		const commentDeleteModal = bootstrap.Modal.getInstance(document.querySelector('#commentDeleteModal'))
		commentDeleteModal.hide()
	}, () => {
		alert('암호가 틀렸습니다.')
	})
}
