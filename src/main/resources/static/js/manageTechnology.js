
// $(document).ready(function(){
// 	// Activate tooltip
// 	$('[data-toggle="tooltip"]').tooltip();
	
// 	// // Select/Deselect checkboxes
// 	// var checkbox = $('table tbody input[type="checkbox"]');
// 	// $("#selectAll").click(function(){
// 	// 	if(this.checked){
// 	// 		checkbox.each(function(){
// 	// 			this.checked = true;                        
// 	// 		});
// 	// 	} else{
// 	// 		checkbox.each(function(){
// 	// 			this.checked = false;                        
// 	// 		});
// 	// 	} 
// 	// });
// 	// checkbox.click(function(){
// 	// 	if(!this.checked){
// 	// 		$("#selectAll").prop("checked", false);
// 	// 	}
// 	// });
// });


function showAddTechnologyModal() {
	Swal.fire({
		title: 'Add Technology',
		input: 'text',
		inputAttributes: {
			autocapitalize: 'off'
		},
		showCancelButton: true,
		confirmButtonText: 'Add',
		showLoaderOnConfirm: true,
		preConfirm: (technology) => {
			// Handle form submission logic (e.g., AJAX request to add technology)
			return new Promise((resolve) => {
				setTimeout(() => {
					// Simulate a successful request
					resolve();
				}, 2000); // Simulate a 2-second delay
			});
		},
		allowOutsideClick: () => !Swal.isLoading()
	}).then((result) => {
		if (result.isConfirmed) {
			// Handle the confirmed action (e.g., show success message)
			Swal.fire('Added!', 'The technology has been added.', 'success');
		}
	});
}
    function showEditTechnologyModal() {
        Swal.fire({
            title: 'Edit Technology',
            input: 'text',
            inputAttributes: {
                autocapitalize: 'off'
            },
            showCancelButton: true,
            confirmButtonText: 'Save',
            showLoaderOnConfirm: true,
            preConfirm: (technology) => {
                // Handle form submission logic (e.g., AJAX request to update technology)
                return new Promise((resolve) => {
                    setTimeout(() => {
                        // Simulate a successful request
                        resolve();
                    }, 2000); // Simulate a 2-second delay
                });
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                // Handle the confirmed action (e.g., show success message)
                Swal.fire('Saved!', 'The technology has been updated.', 'success');
            }
        });
    }
    function showDeleteTechnologyModal() {
        Swal.fire({
            title: 'Delete Technology',
            text: 'Are you sure you want to delete this technology?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Delete',
            showLoaderOnConfirm: true,
            preConfirm: () => {
                // Handle form submission logic (e.g., AJAX request to delete technology)
                return new Promise((resolve) => {
                    setTimeout(() => {
                        // Simulate a successful request
                        resolve();
                    }, 2000); // Simulate a 2-second delay
                });
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                // Handle the confirmed action (e.g., show success message)
                Swal.fire('Deleted!', 'The technology has been deleted.', 'success');
            }
        });
    }

