<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Category bằng Ajax</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="container mt-4">

    <h2 class="mb-3">Danh sách Category</h2>
    <button class="btn btn-success mb-3" onclick="showCreateNewCategoryModal()">+ Thêm Category</button>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Id</th>
                <th>Tên</th>
                <th>Mô tả</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="categoryTable"></tbody>
    </table>

    <!-- Modal Thêm -->
    <div class="modal fade" id="createCategoryModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="addCategoryForm">
                    <div class="modal-header">
                        <h5 class="modal-title">Thêm Category</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <input type="text" id="name" name="name" placeholder="Tên" class="form-control mb-2">
                        <input type="text" id="description" name="description" placeholder="Mô tả" class="form-control">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Thêm</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal Sửa -->
    <div class="modal fade" id="updateCategoryModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="updateCategoryForm">
                    <div class="modal-header">
                        <h5 class="modal-title">Cập nhật Category</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="updateId" name="id">
                        <input type="text" id="updateName" name="name" class="form-control mb-2">
                        <input type="text" id="updateDescription" name="description" class="form-control">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-warning">Cập nhật</button>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

<script type="text/javascript">
    const apiBase = "/api/category";

    // Load danh sách Category
    function loadCategories() {
        $.get(apiBase, function(data) {
            let rows = "";
            data.forEach(cat => {
                rows += `
                    <tr>
                        <td>${cat.id}</td>
                        <td>${cat.name}</td>
                        <td>${cat.description}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" onclick="showUpdateModal(${cat.id}, '${cat.name}', '${cat.description}')">Sửa</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteCategory(${cat.id})">Xóa</button>
                        </td>
                    </tr>`;
            });
            $("#categoryTable").html(rows);
        });
    }

    // Thêm Category
    $("#addCategoryForm").submit(function(e) {
        e.preventDefault();
        $.post(apiBase + "/addCategory", $(this).serialize(), function() {
            $("#createCategoryModal").modal("hide");
            loadCategories();
        });
    });

    // Sửa Category
    function showUpdateModal(id, name, desc) {
        $("#updateId").val(id);
        $("#updateName").val(name);
        $("#updateDescription").val(desc);
        $("#updateCategoryModal").modal("show");
    }

    $("#updateCategoryForm").submit(function(e) {
        e.preventDefault();
        $.ajax({
            url: apiBase + "/updateCategory",
            type: "PUT",
            data: $(this).serialize(),
            success: function() {
                $("#updateCategoryModal").modal("hide");
                loadCategories();
            }
        });
    });

    // Xóa Category
    function deleteCategory(id) {
        if(confirm("Bạn có chắc muốn xóa?")) {
            $.ajax({
                url: apiBase + "/deleteCategory?id=" + id,
                type: "DELETE",
                success: function() {
                    loadCategories();
                }
            });
        }
    }

    // Hiển thị modal Thêm
    function showCreateNewCategoryModal() {
        $("#addCategoryForm")[0].reset();
        $("#createCategoryModal").modal("show");
    }

    // Khi trang load
    $(document).ready(function() {
        loadCategories();
    });
</script>

</body>
</html>
