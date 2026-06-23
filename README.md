# Selenium Automation Testing (Java)

## Chạy test trên bash

1. Cài Java 17 và Maven.
2. Cấp quyền thực thi và chạy script:
   ```bash
   chmod +x run-tests.sh
   ./run-tests.sh
   ```

# BÁO CÁO DỰ ÁN: SELENIUM AUTOMATION TESTING

**Ngày lập báo cáo:** 24/06/2026  
**Phiên bản dự án:** 1.0-SNAPSHOT  
**Trạng thái:** Hoàn thành

---

## 1. TỔNG QUAN DỰ ÁN

### 1.1 Mục tiêu
Xây dựng suite automation testing toàn diện bằng Java và Selenium WebDriver để kiểm thử tự động website demo `https://www.saucedemo.com/`. Suite bao gồm các test case kiểm tra các chức năng cơ bản của ứng dụng e-commerce mẫu.

### 1.2 Công nghệ sử dụng
| Thành phần | Chi tiết |
|-----------|---------|
| **Ngôn ngữ lập trình** | Java 17 (LTS) |
| **Framework kiểm thử** | JUnit 5 (Jupiter 5.10.0) |
| **Automation Tool** | Selenium WebDriver 4.16.0 |
| **WebDriver Manager** | WebDriverManager 5.4.1 |
| **Build Tool** | Maven 3.x |
| **Trình duyệt** | Chrome (Headless mode) |

### 1.3 Phạm vi kiểm thử
- Login thành công
- Logout thành công
- Thêm sản phẩm vào giỏ hàng
- Xóa sản phẩm khỏi giỏ hàng
- Hoàn tất quy trình thanh toán

---

## 2. CẤU TRÚC DỰ ÁN

```
Selenium_Automation_Testing/
├── pom.xml                              # Cấu hình Maven và quản lý dependencies
├── mvnw                                 # Maven Wrapper (Linux/Mac)
├── mvnw.cmd                             # Maven Wrapper (Windows)
├── README.md                            # Tài liệu dự án
├── run-tests.sh                         # Script chạy test tự động
│
├── src/
│   ├── main/
│   │   └── java/com/example/
│   │       └── App.java                 # Ứng dụng chính
│   │
│   └── test/
│       └── java/com/test/
│           ├── BaseTest.java            # Lớp cơ sở cho tất cả test
│           ├── LoginSuccessTest.java    # Test đăng nhập thành công
│           ├── LogoutTest.java          # Test đăng xuất
│           ├── AddProductTest.java      # Test thêm sản phẩm
│           ├── RemoveProductTest.java   # Test xóa sản phẩm
│           └── CheckoutTest.java        # Test thanh toán
│
└── target/                              # Thư mục build (tự động tạo)
      ├── classes/                         # Bytecode chính
      └── test-classes/                    # Bytecode test
```

---

## 3. CHI TIẾT CÁC DEPENDENCIES

### 3.1 Selenium Java
- **Phiên bản:** 4.16.0
- **Mục đích:** Tương tác với các phần tử giao diện web, điều khiển trình duyệt
- **Chức năng chính:**
   - Tìm kiếm phần tử (By ID, XPath, CSS Selector, v.v.)
   - Tương tác với phần tử (click, sendKeys, submit)
   - Quản lý cửa sổ, tab, context
   - Chờ đợi phần tử (Explicit, Implicit waits)

### 3.2 WebDriverManager
- **Phiên bản:** 5.4.1
- **Mục đích:** Tự động tải xuống và quản lý ChromeDriver
- **Lợi ích:** Không cần cấu hình thủ công đường dẫn WebDriver, tự động xử lý version tương thích

### 3.3 JUnit Jupiter
- **Phiên bản:** 5.10.0
- **Mục đích:** Framework test, quản lý lifecycle test (@BeforeAll, @BeforeEach, @AfterEach, v.v.)
- **Chức năng:** Khẳng định (assertions), annotations, lifecycle callbacks

### 3.4 Maven Surefire Plugin
- **Phiên bản:** 3.1.2
- **Mục đích:** Chạy test trong quá trình build Maven
- **Cấu hình:** Tắt module path để tương thích với Java 17+

---

## 4. CẤU HÌNH MÔI TRƯỜNG

### 4.1 Compiler Configuration
```xml
Java Source & Target: 17
Encoding: UTF-8 (mặc định)
```

### 4.2 Chrome Driver Options (Headless Mode)
| Tùy chọn | Mục đích |
|---------|---------|
| `--headless=new` | Chạy ở chế độ không hiển thị giao diện |
| `--disable-gpu` | Vô hiệu hóa GPU acceleration |
| `--window-size=1920x1080` | Đặt kích thước cửa sổ |
| `--no-sandbox` | Vô hiệu hóa sandbox (dùng trong Docker/CI) |

---

## 5. CÁC TEST CASE

### 5.1 Test: Login Success
**File:** [LoginSuccessTest.java](src/test/java/com/test/LoginSuccessTest.java)

| Yếu tố | Chi tiết |
|-------|---------|
| **Mô tả** | Kiểm tra quy trình đăng nhập thành công |
| **URL kiểm thử** | https://www.saucedemo.com/ |
| **Username** | standard_user |
| **Password** | secret_sauce |
| **Kỳ vọng** | Redirected to inventory page, session established |

**Các bước thực hiện:**
1. Mở website
2. Nhập username vào trường user-name
3. Nhập password vào trường password
4. Click nút login-button
5. Xác minh điều hướng thành công

---

### 5.2 Test: Logout
**File:** [LogoutTest.java](src/test/java/com/test/LogoutTest.java)

| Yếu tố | Chi tiết |
|-------|---------|
| **Mô tả** | Kiểm tra quy trình đăng xuất |
| **Điều kiện tiên quyết** | Đã đăng nhập thành công |
| **Kỳ vọng** | Session kết thúc, quay lại login page |

**Các bước thực hiện:**
1. Đăng nhập vào hệ thống
2. Click menu hamburger
3. Chọn "Logout"
4. Xác minh quay lại login page

---

### 5.3 Test: Add Product to Cart
**File:** [AddProductTest.java](src/test/java/com/test/AddProductTest.java)

| Yếu tố | Chi tiết |
|-------|---------|
| **Mô tả** | Kiểm tra thêm sản phẩm vào giỏ hàng |
| **Điều kiện tiên quyết** | Đã đăng nhập, ở inventory page |
| **Kỳ vọng** | Sản phẩm được thêm vào cart, badge count cập nhật |

**Các bước thực hiện:**
1. Đăng nhập vào hệ thống
2. Chọn sản phẩm (ví dụ: "Sauce Labs Backpack")
3. Click "Add to cart"
4. Xác minh badge số lượng trong giỏ hiển thị
5. Mở giỏ hàng để xác minh sản phẩm

---

### 5.4 Test: Remove Product from Cart
**File:** [RemoveProductTest.java](src/test/java/com/test/RemoveProductTest.java)

| Yếu tố | Chi tiết |
|-------|---------|
| **Mô tả** | Kiểm tra xóa sản phẩm khỏi giỏ hàng |
| **Điều kiện tiên quyết** | Đã thêm sản phẩm vào cart |
| **Kỳ vọng** | Sản phẩm bị xóa, badge count giảm |

**Các bước thực hiện:**
1. Thêm sản phẩm vào giỏ
2. Mở giỏ hàng
3. Click "Remove"
4. Xác minh sản phẩm bị xóa
5. Xác minh badge count cập nhật

---

### 5.5 Test: Checkout Process
**File:** [CheckoutTest.java](src/test/java/com/test/CheckoutTest.java)

| Yếu tố | Chi tiết |
|-------|---------|
| **Mô tả** | Kiểm tra quy trình thanh toán hoàn chỉnh |
| **Điều kiện tiên quyết** | Có sản phẩm trong giỏ hàng |
| **Kỳ vọng** | Thanh toán thành công, nhận order confirmation |

**Các bước thực hiện:**
1. Thêm sản phẩm vào giỏ
2. Mở giỏ hàng
3. Click "Checkout"
4. Nhập thông tin giao hàng (First Name, Last Name, Postal Code)
5. Click "Continue"
6. Xác minh Order Summary
7. Click "Finish"
8. Xác minh "Thank you for your order" message

---

## 6. HƯỚNG DẪN CHẠY TEST

### 6.1 Yêu cầu hệ thống
- **Java Development Kit:** JDK 17 hoặc cao hơn
- **Maven:** 3.6.0 hoặc cao hơn
- **Chrome Browser:** Phiên bản mới nhất
- **Hệ điều hành:** Windows, macOS, Linux
- **Internet connection:** Bắt buộc (truy cập website demo)

### 6.2 Cài đặt

#### Trên Windows:
```bash
# Clone hoặc extract dự án
cd Selenium_Automation_Testing

# Chạy Maven để tải dependencies (lần đầu)
mvn clean install
```

#### Trên macOS/Linux:
```bash
cd Selenium_Automation_Testing
chmod +x mvnw
./mvnw clean install
```

### 6.3 Chạy tất cả test

#### Sử dụng Maven command:
```bash
mvn test
```

#### Sử dụng script (Linux/macOS):
```bash
chmod +x run-tests.sh
./run-tests.sh
```

#### Sử dụng Maven Wrapper (Windows):
```bash
mvnw.cmd test
```

### 6.4 Chạy một test cụ thể
```bash
# Chạy chỉ LoginSuccessTest
mvn test -Dtest=LoginSuccessTest

# Chạy chỉ AddProductTest
mvn test -Dtest=AddProductTest
```

### 6.5 Chạy test với báo cáo chi tiết
```bash
# Chạy và tạo báo cáo Surefire
mvn test -DskipTests=false -X

# Xem báo cáo tại: target/surefire-reports/
```

---

## 7. CẤU TRÚC BASETEST CLASS

### 7.1 Mục đích
- Cung cấp setup/teardown chung cho tất cả test
- Khởi tạo WebDriver
- Cấu hình Chrome options
- Cung cấp method `login()` tái sử dụng

### 7.2 Lifecycle
```
@BeforeAll (một lần)
   ↓
[@BeforeEach (trước mỗi test)
   ↓
 Test execution
   ↓
 @AfterEach (sau mỗi test)]
```

### 7.3 Protected Methods
- `login(String baseUrl, String username, String password)` - Helper method cho login

---

## 8. TỐI ƯU HÓA VÀ BEST PRACTICES

### 8.1 Headless Mode
✅ **Lợi ích:**
- Chạy nhanh hơn (không render giao diện)
- Phù hợp cho CI/CD pipeline
- Tiết kiệm tài nguyên hệ thống
- Không cần màn hình X11 trên Linux

### 8.2 WebDriverManager
✅ **Lợi ích:**
- Tự động quản lý ChromeDriver version
- Tránh lỗi version mismatch
- Code sạch, không cần cấu hình thủ công

### 8.3 Wait Strategy
⚠️ **Khuyến nghị:**
- Sử dụng Explicit Waits thay vì Implicit Waits
- Ví dụ: `WebDriverWait(driver, Duration.ofSeconds(10))`

### 8.4 Xử lý Exception
✅ **Best Practice:**
- Luôn gọi `driver.quit()` trong `@AfterEach`
- Sử dụng try-finally hoặc `@AfterEach` để đảm bảo cleanup

---

## 9. KẾT QUẢ VÀ CÁC METRICS

### 9.1 Quy mô Test
| Metric | Giá trị |
|--------|--------|
| **Tổng số test case** | 5 |
| **Test classes** | 5 |
| **Base test class** | 1 |
| **Tổng lines of test code** | ~500+ |
| **Test coverage scenarios** | 5 use cases chính |

### 9.2 Thời gian chạy (ước tính)
| Scenario | Thời gian |
|----------|----------|
| **Mỗi test** | 5-10 giây |
| **Toàn bộ suite** | 25-50 giây |
| **Với CI/CD overhead** | ~2-3 phút |

### 9.3 Độ tin cậy
- **Stability:** Cao (website demo ổn định)
- **Flakiness:** Thấp (ít phụ thuộc network timing)

---

## 10. TROUBLESHOOTING

### 10.1 Lỗi: "Driver executable does not exist"
**Nguyên nhân:** WebDriverManager không tải ChromeDriver  
**Giải pháp:** 
```bash
mvn clean
mvn test
```

### 10.2 Lỗi: "No tests were executed"
**Nguyên nhân:** Test class không match pattern  
**Giải pháp:** Đảm bảo class kết thúc bằng `Test` (ví dụ: `LoginTest.java`)

### 10.3 Lỗi: "Element not found after wait"
**Nguyên nhân:** Phần tử không được tìm thấy  
**Giải pháp:** Tăng wait time, kiểm tra selector, verify network connection

### 10.4 Connection Timeout
**Nguyên nhân:** Không kết nối được website demo  
**Giải pháp:** Kiểm tra Internet connection, Proxy settings

---

## 11. MỞ RỘNG VÀ PHÁT TRIỂN SAU

### 11.1 Tính năng có thể bổ sung
- ✅ Test data-driven với CSV/Excel
- ✅ Cross-browser testing (Firefox, Safari, Edge)
- ✅ Page Object Model (POM) pattern
- ✅ Test reporting (Allure, ExtentReports)
- ✅ API testing (REST endpoints)
- ✅ Performance testing
- ✅ Integration với CI/CD (Jenkins, GitHub Actions)

### 11.2 Tích hợp CI/CD
```yaml
# GitHub Actions example
- name: Run Selenium Tests
   run: mvn test
```

---

## 12. LIÊN HỆ VÀ HỖ TRỢ

**Dự án:** Selenium Automation Testing Suite  
**Phiên bản:** 1.0-SNAPSHOT  
**Java Version:** 17 LTS  
**Selenium Version:** 4.16.0  
**Ngày cập nhật cuối:** 24/06/2026

---

**Lưu ý:** Đây là dự án demo. Để sử dụng trên production, cần thêm logging, error handling, reporting, và security configurations.
