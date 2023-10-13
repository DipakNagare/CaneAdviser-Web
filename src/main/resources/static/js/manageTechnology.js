

function addTechnology(technology) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8081/group-masters",
        data: JSON.stringify({ groupName: technology }),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            // Technology added successfully, update the table
            updateTable();
        },
        error: function (error) {
            // Handle the error, e.g., display an error message
            Swal.fire('Error', 'Failed to add technology', 'error');
        }
    });
}


function updateTechnology(groupId, updatedTechnology) {
    $.ajax({
        type: "PUT",
        url: `http://localhost:8081/group-masters/${groupId}`,
        data: JSON.stringify({ groupName: updatedTechnology }), // JSON payload
        contentType: "application/json; charset=utf-8", // Content-Type header
        success: function (data) {
            // Handle the success response
        updateTable();

        },
        error: function (error) {
            // Handle the error response
        }
    });
}




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
            return new Promise((resolve) => {
                // Call the addTechnology function to add the technology
                addTechnology(technology);
                resolve();
            });
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            // Handle the confirmed action (e.g., show success message) if needed
            Swal.fire('Added!', 'The technology has been added.', 'success');
        }
    }); 
}

function showEditTechnologyModal(srNo) {
    // Fetch the existing data for the technology based on the provided Sr.No
    const groupId = srNo; // Use Sr.No to identify the technology

    // Make an AJAX GET request to retrieve the current data for the technology
    $.get(`/group-masters/${groupId}`, function (existingData) {
        Swal.fire({
            title: 'Edit Technology',
            input: 'text',
            inputAttributes: {
                autocapitalize: 'off'
            },
            inputValue: existingData.groupName, // Pre-fill the input with existing data
            showCancelButton: true,
            confirmButtonText: 'Save',
            showLoaderOnConfirm: true,
            preConfirm: (updatedTechnology) => {
                return new Promise((resolve) => {
                    // Call the updateTechnology function to update the technology
                    updateTechnology(groupId, updatedTechnology);
                    resolve();
                });
            },
            allowOutsideClick: () => !Swal.isLoading()
        }).then((result) => {
            if (result.isConfirmed) {
                // Handle the confirmed action (e.g., show success message) if needed
                Swal.fire('Saved!', 'The technology has been updated.', 'success');
            }
        });
    });
}

function showDeleteTechnologyModal(groupId) {
    Swal.fire({
        title: 'Delete Technology',
        text: 'Are you sure you want to delete this technology?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Delete',
        showLoaderOnConfirm: true,
        preConfirm: () => {
            // Make a DELETE request to the API to delete the technology
            return new Promise((resolve) => {
                // Send a DELETE request to delete the technology
                $.ajax({
                    type: 'DELETE',
                    url: `/group-masters/${groupId}`,
                    success: function () {
					    updateTable();
                        resolve();
                    },
                    error: function () {
                        // Handle the error, e.g., show an error message
                        Swal.fire('Error', 'Failed to delete the technology.', 'error');
                    },
                });
            });
        },
        allowOutsideClick: () => !Swal.isLoading(),
    }).then((result) => {
        if (result.isConfirmed) {
            // Handle the confirmed action, e.g., show a success message
            Swal.fire('Deleted!', 'The technology has been deleted.', 'success');
        }
    });
}
function updateTable() {
    $.get("/group-masters", function (data) {
        var dataContainer = $("#data-container");
        dataContainer.html(""); // Clear existing content

        var idCounter = 1;
        
        $.each(data, function (index, groupMaster) {
            var row = `
                <tr data-srno="${idCounter}">
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">${idCounter}</td>
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">${groupMaster.groupName}</td>
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">
                        <a href="#editEmployeeModal" class="edit" onclick="showEditTechnologyModal(${groupMaster.groupId})" data-toggle="modal" style="color: inherit;"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                        <a href="#deleteEmployeeModal" class="delete" onclick="showDeleteTechnologyModal(${groupMaster.groupId})" data-toggle="modal" style="color: inherit;"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                    </td>
                </tr>`;
            dataContainer.append(row);
            idCounter++;
        });
    });
}