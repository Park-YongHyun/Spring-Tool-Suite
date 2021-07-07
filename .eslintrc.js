module.exports = {
	env: {
		browser: true,
		es2021: true
	},
	extends: [
		'standard'
	],
	parserOptions: {
		ecmaVersion: 12
	},
	rules: {
		'no-tabs': 'off',
		indent: ['error', 'tab'],
		'prefer-const': 'warn',
		'no-undef': 'off',
		'no-multiple-empty-lines': ['error', { max: 2 }],
		'no-unused-vars': 'off'
	}
}
