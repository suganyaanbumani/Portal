// Include Gulp
var gulp = require('gulp');
var concat = require('gulp-concat');

//Include plugins
var plugins = require("gulp-load-plugins")({
	pattern: ['gulp-*', 'gulp.*', 'bower*']
});

var mainBowerFiles = require('main-bower-files');

//Define default destination folder
var dest = './build';



gulp.task('js', function() {

	var jsFiles = ['public/bower_components/**/*.js'];

	gulp.src(mainBowerFiles({
		    paths: {
		        bowerDirectory: 'public/bower_components',
		        bowerJson: 'public/bower.json'
		    }
		}))
		.pipe(plugins.filter('**/*.js'))
		.pipe(plugins.uglify())
		.pipe(concat('vendor.js'))
		.pipe(gulp.dest('public/js'));

});

gulp.task('css', function() {
	gulp.src(mainBowerFiles({
	    paths: {
	        bowerDirectory: 'public/bower_components',
	        bowerJson: 'public/bower.json'
	    }
	}))
	.pipe(plugins.filter('**/*.css'))
	.pipe(concat('vendor.css'))
	.pipe(gulp.dest('public/css'));
});

gulp.task('default', function() {
    gulp.start('css', 'js');
});




