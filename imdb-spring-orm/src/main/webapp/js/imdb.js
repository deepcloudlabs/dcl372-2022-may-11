class ImdbViewModel {
	constructor() {
		this.movies = ko.observableArray([]);
		this.fromYear = ko.observable(1970);
		this.toYear = ko.observable(1979);
	}

	list = () => {
		fetch(`http://localhost:5100/imdb/api/v1/movies?fromYear=${this.fromYear()}&toYear=${this.toYear()}`, {
			headers: {
				"Accept": "application/json"
			}
		})
		.then(res => res.json())
		.then(movies => {
			movies.sort((left, right) => left.title.localeCompare(right.title));
			this.movies(movies);
		});
	}
}

let imdbViewModel = new ImdbViewModel();

window.onload = () => {
	ko.applyBindings(imdbViewModel);
	imdbViewModel.init();
}