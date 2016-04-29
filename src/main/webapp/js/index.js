(function ($) {
	'use strict';

	var userAPI = {};

	var getUserHTML = function (user) {
		return 	'<tr>' +
					'<td>' + user.id + '</td>' +
					'<td>' + user.name + '</td>' +
					'<td>' + user.lastName + '</td>' +
					'<td class="edit" data-id="' + user.id + '">Edit</td>' +
					'<td class="remove" data-id="' + user.id + '">Remove</td>'
				'</tr>';
	};

	userAPI.list = function () {
		$.getJSON('/api/user', function (users) {
			$('.user-table tbody').empty()
			for (var i = 0; i < users.length; i++) {
				$('.user-table tbody').append(getUserHTML(users[i]));
			}
			$('form').removeData('id');
			$('.form-title').text('New user');
			$('input').val('').change();
		});
	};

	userAPI.get = function (id) {
		$.getJSON('/api/user/' + id, function (user) {
			$('.form-title').text('Edit user');
			$('form').data('id', user.id);
			$('#first_name').val(user.name).focus();
			$('#last_name').val(user.lastName).change();
		});
	};

	userAPI.save = function (id, user) {
		$.ajax({
			url : '/api/user/' + (id ? id : ''),
			type : id ? 'PUT' : 'POST',
			data : {
				user : JSON.stringify(user)
			}
		}).done(function () {
			userAPI.list();
		});
	};

	userAPI.remove = function (id) {
		$.ajax({
			url : '/api/user/' + id,
			type : 'DELETE'
		}).done(function () {
			userAPI.list();
		});
	};

	userAPI.init = function () {
		userAPI.list();

		$('table').on('click', 'tbody tr .edit', function () {
			userAPI.get($(this).data('id'));
		});

		$('table').on('click', 'tbody tr .remove', function () {
			userAPI.remove($(this).data('id'));
		});

		$('.btn.save-user').click(function () {
			userAPI.save($('form').data('id'), {name : $('#first_name').val(), lastName : $('#last_name').val()});
		});
	};

	window.userAPI = userAPI;

}(window.jQuery));
