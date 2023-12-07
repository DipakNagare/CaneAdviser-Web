$(document).ready(function () {
    // Event handler for the "Add Expert" button
    $('.btn-success').click(function () {
        openAddExpertModal();
    });

    // Function to open the SweetAlert modal
    function openAddExpertModal() {
        Swal.fire({
            title: 'Add Expert',
            html: getAddExpertForm(),
            showCloseButton: true,
            showConfirmButton: true,
            confirmButtonText: 'Add Expert',
            customClass: 'swal-wide', // Adjust the width if needed
            preConfirm: () => {
                // Handle the form submission here
                const name = $('#name').val();
                const email = $('#userId').val();
                const contactNo = $('#contactNo').val();
                const gender = $('#gender').val();
                const status = $('#status')

                // Validate the form data
                if (!name || !email || !contactNo || !gender || !status) {
                    Swal.showValidationMessage('Please fill in all fields');
                    return;
                }
                // Make an AJAX request to the server to save the user
                $.ajax({
                    type: 'POST',
                    url: '/user-masters/create',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        name: $("#name").val(),
                        userId: $("#userId").val(), // Update this field to set the userId
                        contactNo: $("#contactNo").val(),
                        email: $("#userId").val(),
                        gender: $("#gender").val(),
                        status: $("#status").val()

                    }),
                    success: function (data) {
                        // Handle the success response (if needed)
                        Swal.fire('Success!', 'User added successfully!', 'success');

                    },
                    error: function (error) {
                        Swal.fire('Error!', 'Failed to add user. Please try again.', 'error');
                        // Swal.showValidationMessage('Error adding user');
                    }
                });
            }
        });
    }

    // Function to get the HTML content for the Add Expert form
    function getAddExpertForm() {
        return `
<div class="form-wrap" style="background: rgba(255,255,255,1); width: 100%; max-width: 850px; padding: 50px; margin: 0 auto; margin-top: 20px; position: relative; border-radius: 10px; box-shadow: 0px 0px 40px rgba(0, 0, 0, 0.15);">
<form id="addExpertForm">
<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label for="name" style="display: block; font-size: 18px; color: #000;">Name</label>
            <input type="text" name="name" id="name" placeholder="Enter your Full Name" class="form-control"
                required
                style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-group">
            <label for="userId" style="display: block; font-size: 18px; color: #000;">Email</label>
            <input type="text" name="userId" class="form-control" id="userId" placeholder="User Id" required
                style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label style="font-size: 18px;">Gender</label>
            <select id="gender" name="gender" class="form-control" required
                style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
                <option disabled selected value>Select</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-group">
            <label for="contactNo" style="display: block; font-size: 18px; color: #000;">Contact No</label>
            <input type="text" name="contactNo" id="contactNo" placeholder="Contact No" class="form-control"
                required pattern="[0-9]{10}"
                style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
    <div class="form-group">
    <label style="font-size: 18px;">Status</label>
    <select id="status" name="status" class="form-control" required
        style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
        <option disabled selected value>Select</option>
        <option value="Active">Active</option>
        <option value="Unactive">Unactive</option>
    </select>
</div>
    </div>
    </div>
</form>
</div>
    `;
    }
});

/////////////////////////////////////////////////////////




var currentPage = 1;
var totalPages = 1; // Define totalPages here
var itemsPerPage = 10; // Define itemsPerPage here

$(document).ready(function () {
    $("#nextBtn").on("click", function () {
        if (currentPage < totalPages) {
            currentPage++;
            updateTable();
        }
    });

    $("#prevBtn").on("click", function () {
        if (currentPage > 1) {
            currentPage--;
            updateTable();
        }
    });

    // Fetch data from the API endpoint
    $.get("/user-masters", function (data) {
        var dataContainer = $("#data-container");
        totalPages = Math.ceil(data.length / itemsPerPage);
        updateTable();

        if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        updateTableContent(data);
    });
});

$("#pagination").on("click", "li.page-item", function () {
    var page = $(this).index() - 1; // Subtract 1 for the previous button
    if (page >= 0 && page < totalPages) {
        currentPage = page + 1;
        updateTable();
    }
});

function updateTable() {
    // You can perform any necessary updates here
    // For now, let's assume you want to fetch data again for the current page
    $.get("/user-masters", function (data) {
        updateTableContent(data);
    });
}

function updateTableContent(data) {
    var dataContainer = $("#data-container");
    dataContainer.html(""); // Clear existing content

    // console.log('Data received:', data); // Add this line for debugging

    // Populate table rows with data
    for (var i = (currentPage - 1) * itemsPerPage; i < Math.min(currentPage * itemsPerPage, data.length); i++) {
        var expert = data[i];
        if (expert && expert.userId) {
            // console.log('Expert userId:', expert.userId);
            var row = `
                <tr data-srno="${i + 1}">
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">${i + 1}</td>
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">${expert.name || 'N/A'}</td>
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">${expert.email || 'N/A'}</td>
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">${expert.contactNo || 'N/A'}</td>
                    <td style="border-color: #e9e9e9; padding: 12px 15px; vertical-align: middle;">
                        <a href="#editEmployeeModal" class="edit" onclick="showEditExpertModal('${expert.userId}')" data-toggle="modal" style="color: inherit;">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i>
                        </a>
                        <a href="javascript:void(0);" class="delete" onclick="deleteExpert('${expert.userId}')" style="color: #cd282b;">
                            <i class="material-icons delete-icon" data-toggle="tooltip" title="Delete">&#xE872;</i>
                        </a>
                    </td>
                </tr>`;
            dataContainer.append(row);
        } else {
            console.log('Invalid expert data at index', i, ':', expert);
        }
    }
}

function showEditExpertModal(userId) {
    // Fetch the expert data by userId
    $.get(`/user-masters/${userId}`, function (expert) {
        // Check if the expert data is valid
        if (expert && expert.userId) {
            // Populate the modal with the existing data
            $('#editName').val(expert.name || '');
            $('#editUserId').val(expert.userId);
            $('#editContactNo').val(expert.contactNo || '');
            $('#editEmail').val(expert.email || '');

            // Show the edit modal
            $('#editExpertModal').modal('show');
        } else {
            console.log('Invalid expert data for editing:', expert);
            // Handle the case where the expert data is invalid
        }
    });
}

////////////////////////////////////////////////////////////////////////////

// Function to open the SweetAlert modal for editing an expert
window.showEditExpertModal = function (userId) {
    // Fetch the expert data by userId
    $.get(`/user-masters/${userId}`, function (expert) {
        // Check if the expert data is valid
        if (expert && expert.userId) {
            // Show the SweetAlert modal for editing
            Swal.fire({
                title: 'Edit Expert',
                html: getEditExpertForm(expert),
                showCloseButton: true,
                showConfirmButton: true,
                confirmButtonText: 'Save Changes',
                customClass: 'swal-wide', // Adjust the width if needed
                preConfirm: () => {
                    // Handle the form submission here
                    const name = $('#editName').val();
                    const contactNo = $('#editContactNo').val();
                    const email = $('#editEmail').val();

                    // Validate the form data
                    if (!name || !contactNo || !email) {
                        Swal.showValidationMessage('Please fill in all fields');
                        return;
                    }

                    // Make an AJAX request to update the expert data
                    $.ajax({
                        type: 'PUT',
                        url: `/user-masters/${userId}`,
                        contentType: 'application/json',
                        data: JSON.stringify({
                            name: name,
                            contactNo: contactNo,
                            email: email
                        }),
                        success: function () {
                            // Update the table after successful update
                            updateTable();
                            Swal.fire('Success!', 'Expert data updated successfully!', 'success');
                        },
                        error: function () {
                            Swal.fire('Error!', 'Failed to update expert. Please try again.', 'error');
                            // Handle the error case
                        }
                    });
                }
            });
        } else {
            console.log('Invalid expert data for editing:', expert);
            // Handle the case where the expert data is invalid
        }
    });
}

function getEditExpertForm(expert) {
    return `
<div class="form-wrap" style="background: rgba(255,255,255,1); width: 100%; max-width: 850px; padding: 50px; margin: 0 auto; margin-top: 20px; position: relative; border-radius: 10px; box-shadow: 0px 0px 40px rgba(0, 0, 0, 0.15);">
<form id="editExpertForm">
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label for="editName" style="display: block; font-size: 18px; color: #000;">Name</label>
                <input type="text" name="editName" id="editName" value="${expert.name || ''}" class="form-control"
                    required
                    style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label for="editUserId" style="display: block; font-size: 18px; color: #000;">User ID</label>
                <input type="text" name="editUserId" class="form-control" id="editUserId" value="${expert.userId || ''}" readonly>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="form-group">
                <label for="editContactNo" style="display: block; font-size: 18px; color: #000;">Contact No</label>
                <input type="text" name="editContactNo" id="editContactNo" value="${expert.contactNo || ''}" class="form-control"
                    required pattern="[0-9]{10}"
                    style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
            </div>
        </div>

        <div class="col-md-6">
        <div class="form-group">
            <label for="editEmail" style="display: block; font-size: 18px; color: #000;">Email</label>
            <input type="email" name="editEmail" id="editEmail" value="${expert.email || ''}" class="form-control"
                required
                style="height: 50px; background: #ecf0f4; border-color: transparent; padding: 0 15px; font-size: 16px; transition: all 0.3s ease-in-out;">
        </div>
    </div>
    </div>
</form>
</div>
`;
}
///////////////////////////////////////////////////////////////////////////////

function deleteExpert(expertId) {
    // console.log('Expert to be deleted:', expertId);
    // Assuming your delete endpoint is '/user-masters/'
    // var deleteUrl = '/user-masters/' + expertId;

    Swal.fire({
        title: 'Delete Expert',
        text: 'Are you sure you want to delete this expert?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            // Make an AJAX request to delete the expert
            $.ajax({
                type: 'DELETE',
                url: `/user-masters/${expertId}`,
                success: function () {
                    // Update the table after successful deletion
                    updateTable();
                    Swal.fire('Deleted!', 'Expert has been deleted.', 'success');
                },
                error: function () {
                    Swal.fire('Error!', 'Failed to delete expert. Please try again.', 'error');
                }
            });
        }
    });
}