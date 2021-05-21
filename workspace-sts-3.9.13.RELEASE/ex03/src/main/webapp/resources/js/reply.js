console.log('Reply Module.........')

// 댓글 서비스
let replyService = (() => {
	// 댓글 등록
	function add (reply, callback, error) {
		console.log('add reply..........')
		let xhr = new XMLHttpRequest()
		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					if (callback) {
						callback(xhr.responseText)
					}
				} else {
					if (error) {
						error(xhr)
					}
				}
			}
		}
		xhr.open('post', '/replies/new', true)
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
		xhr.send(JSON.stringify(reply))
	}

	// 댓글 조회
	function get (rno, callback, error) {
		console.log('get..........')
		let xhr = new XMLHttpRequest()
		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					if (callback) {
						callback(JSON.parse(xhr.responseText))
					}
				} else {
					if (error) {
						error(xhr)
					}
				}
			}
		}
		xhr.open('get', `/replies/${rno}.json`, true)
		xhr.send()
	}

	// 게시물의 댓글 조회
	function getList (param, callback, error) {
		console.log('get list..........')
		let xhr = new XMLHttpRequest()
		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					if (callback) {
						callback(JSON.parse(xhr.responseText))
					}
				} else {
					if (error) {
						error(xhr)
					}
				}
			}
		}
		xhr.open('get', `/replies/pages/${param.bno}/${param.page || 1}.json`, true)
		xhr.send()
	}

	// 댓글 수정
	function update (reply, callback, error) {
		console.log('update..........')
		let xhr = new XMLHttpRequest()
		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					if (callback) {
						callback(xhr.responseText)
					}
				} else {
					if (error) {
						error(xhr)
					}
				}
			}
		}
		xhr.open('put', `/replies/${reply.rno}`, true)
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
		xhr.send(JSON.stringify(reply))
	}

	// 댓글 삭제
	function remove (rno, callback, error) {
		console.log('remove..........')
		let xhr = new XMLHttpRequest()
		xhr.onreadystatechange = () => {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					if (callback) {
						callback(xhr.responseText)
					}
				} else {
					if (error) {
						error(xhr)
					}
				}
			}
		}
		xhr.open('delete', `/replies/${rno}`, true)
		xhr.send()
	}

	return {
		add: add,
		get: get,
		getList: getList,
		update: update,
		remove: remove
	}
})()
