export const uri = '/graphql';

export function request(query) {
	var dados = fetch(uri, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({ query }),
	}).then(res => res.json())
	.then(res => dados=res.data);
	return dados;
};