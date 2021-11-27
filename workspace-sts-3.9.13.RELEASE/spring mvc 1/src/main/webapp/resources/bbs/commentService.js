const commentService = {
	writeComment: function (params, callback, error) {
		// postNum, content, writer, password, parent
		const xhr = new XMLHttpRequest()
		xhr.addEventListener('load', () => {
			if (xhr.status === 200) {
				callback(xhr)
			} else {
				if (typeof error !== 'undefined') {
					error(xhr)
				}
			}
		})
		xhr.open('post', `${basePath}/bbs/comment/write`, true)
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
		xhr.send(JSON.stringify(params))
	},

	readCommentList: function (params, callback, error) {
		// postNum, pageNum, pageSize
		const urlParams = new URLSearchParams()
		urlParams.set('postNum', params.postNum)
		urlParams.set('pageNum', params.pageNum)
		urlParams.set('pageSize', params.pageSize)

		const xhr = new XMLHttpRequest()
		xhr.addEventListener('load', () => {
			if (xhr.status === 200) {
				callback(xhr)
			} else {
				if (typeof error !== 'undefined') {
					error(xhr)
				}
			}
		})
		xhr.open('get', `${basePath}/bbs/comment/readList?${urlParams.toString()}`, true)
		xhr.send()
	},

	editComment: function (params, callback, error) {
		// num, content, password
		const xhr = new XMLHttpRequest()
		xhr.addEventListener('load', () => {
			if (xhr.status === 200) {
				callback(xhr)
			} else {
				if (typeof error !== 'undefined') {
					error(xhr)
				}
			}
		})
		xhr.open('put', `${basePath}/bbs/comment/edit`, true)
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
		xhr.send(JSON.stringify(params))
	},

	deleteComment: function (params, callback, error) {
		// num, password
		const xhr = new XMLHttpRequest()
		xhr.addEventListener('load', () => {
			if (xhr.status === 200) {
				callback(xhr)
			} else {
				if (typeof error !== 'undefined') {
					error(xhr)
				}
			}
		})
		xhr.open('delete', `${basePath}/bbs/comment/delete`, true)
		xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8')
		xhr.send(JSON.stringify(params))
	}
}
