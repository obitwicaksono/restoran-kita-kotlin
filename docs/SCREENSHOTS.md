# 📸 Restoran Kita - Application Screenshots

Complete visual guide to the Restoran Kita application with descriptions of each screen and the features demonstrated.

---

## 📱 Application Walkthrough

### 1. Home Screen

**File:** `app/src/main/java/com/example/ui/home/HomeScreen.kt`

**Purpose:** Welcome screen and main navigation hub

**Features Shown:**
- ✅ Restaurant name displayed from SharedPreferences
- ✅ Welcome message/greeting with warm design
- ✅ Two primary navigation buttons:
  - "Lihat Menu" button → navigates to MenuScreen
  - "Profil Restoran" button → navigates to ProfileScreen
- ✅ Restaurant branding and welcoming visual design

**Technical Details:**
- Restaurant name is read from `PreferencesManager.getRestoranName()`
- Smooth navigation with slideInHorizontally + fadeIn animations
- Responsive layout using Jetpack Compose's LazyColumn

**Assignment Coverage:**
- Meets: HomeScreen requirement (display name, navigation buttons)
- Vibe: Professional, welcoming UI design

---

### 2. Menu Screen

**File:** `app/src/main/java/com/example/ui/menu/MenuScreen.kt`

**Purpose:** Display all restaurant menu items with search and filtering

**Features Shown:**
- ✅ Displays 5+ hardcoded MenuItem items
- ✅ Each menu item shows:
  - Item name
  - Price in Indonesian Rupiah (Rp format)
  - Thumbnail image or placeholder icon
- ✅ Search bar for real-time filtering by item name
- ✅ Category filter buttons (Makanan/Minuman/All)
- ✅ Each item is clickable → navigates to DetailMenuScreen

**Menu Items Displayed:**
1. Nasi Goreng Istimewa (Rp 45.000)
2. Mie Ayam Kuah (Rp 35.000)
3. Soto Ayam (Rp 40.000)
4. Es Teh Manis (Rp 10.000)
5. Jus Mangga (Rp 15.000)

**Technical Details:**
- Menu items are hardcoded in `MenuRepository.getMenuItems()`
- Search/filter uses StateFlow for reactive list updates
- Tap any item to navigate to `menu/{itemId}` route
- Smooth list animations with lazy loading

**Assignment Coverage:**
- Meets: MenuScreen requirement (5+ items, names, prices, images, click navigation)
- Vibe: Search and filtering functionality (bonus enhancement)

**Related Code Files:**
- `data/MenuItem.kt` - Data model
- `data/MenuRepository.kt` - Hardcoded menu list
- `ui/RestoranViewModel.kt` - Search state management

---

### 3. Detail Menu Screen

**File:** `app/src/main/java/com/example/ui/detail/DetailMenuScreen.kt`

**Purpose:** Display full menu item details with interactive rating system

**Features Shown:**
- ✅ Full menu item details:
  - Large item image
  - Item name
  - Price in Rp format
  - Detailed description
- ✅ **Interactive 5-star rating system** (Vibe feature)
  - Tap any star to rate (1-5 stars)
  - Visual feedback with color change (yellow for filled, gray for empty)
  - Rating is persisted to SharedPreferences
- ✅ "Kembali ke Menu" button → back to MenuScreen
- ✅ Item details loaded from route argument: `menu/{menuId}`

**Rating System Details:**
- Ratings are stored per item with key: `rating_[menuId]`
- Example: `rating_nasi-goreng`, `rating_mie-ayam`, etc.
- Ratings persist across app restarts via SharedPreferences
- On screen load, previous rating is displayed

**Technical Details:**
- Menu ID passed via NavController: `navController.navigate("menu/$itemId")`
- Rating value retrieved from: `viewModel.itemRatings[menuId]`
- Rating saved via: `PreferencesManager.saveRating(menuId, newRating)`
- Smooth animation on rating selection

**Assignment Coverage:**
- Meets: DetailMenuScreen requirement (full details, back button, route argument)
- Meets: Vibe Coding - "Rating bintang interaktif di DetailMenu" (interactive star rating)

**Related Code Files:**
- `navigation/NavGraph.kt` - Route definition: `composable(route = "menu/{menuId}", ...)`
- `data/PreferencesManager.kt` - Rating persistence: `saveRating()`, `getRating()`
- `ui/RestoranViewModel.kt` - Rating state: `itemRatings` StateFlow

---

### 4. Profile Screen (View 1)

**File:** `app/src/main/java/com/example/ui/profile/ProfileScreen.kt`

**Purpose:** Display restaurant profile data with theme toggle

**Features Shown:**
- ✅ All profile data displayed:
  - Restaurant name
  - Full address
  - Restaurant description/about
  - Operating hours (jam buka)
- ✅ **Theme toggle button** (Vibe feature)
  - Toggle between Light and Dark mode
  - Current theme displayed on button
  - Theme choice persisted to SharedPreferences
- ✅ "Edit Profil" button → navigates to EditProfileScreen
- ✅ Clean, readable layout with proper spacing

**Data Source:**
All data is read from SharedPreferences:
- `restoran_name` → displays restaurant name
- `restoran_alamat` → displays address
- `restoran_deskripsi` → displays description
- `restoran_jam_buka` → displays hours
- `theme_mode` → determines light/dark mode

**Theme Toggle Implementation:**
```kotlin
// Current implementation
Button(onClick = { viewModel.toggleTheme() }) {
    Text(if (isDarkMode) "☀️ Light Mode" else "🌙 Dark Mode")
}

// onClick handler:
// 1. Updates isDarkMode StateFlow
// 2. Saves new value to SharedPreferences
// 3. All screens recompose with new colors
```

**Technical Details:**
- ProfileScreen has LaunchedEffect to reload data on entry
- Ensures fresh data after EditProfileScreen saves changes
- Theme affects entire app (all 6 screens)
- Smooth color transitions via Material Design 3

**Assignment Coverage:**
- Meets: ProfileScreen requirement (display all data from SharedPreferences)
- Meets: Vibe Coding - "Tema gelap/terang dengan toggle" (dark/light theme toggle)

**Related Code Files:**
- `data/PreferencesManager.kt` - Load/save profile data
- `ui/theme/Color.kt` - Light and Dark color schemes
- `ui/theme/Theme.kt` - Material Design 3 theme configuration
- `ui/RestoranViewModel.kt` - Theme state management

---

### 5. Profile Screen (View 2 - Alternative)

**File:** `app/src/main/java/com/example/ui/profile/ProfileScreen.kt`

**Purpose:** Alternative view of ProfileScreen showing different profile data

**Features Shown:**
- Same layout as Profile Screen (View 1)
- May display different restaurant profile data (if user edited profile)
- Theme toggle remains accessible
- Demonstrates ProfileScreen's responsiveness to data updates

**Technical Details:**
- If user came from EditProfileScreen, this shows updated data
- MainActivity.onResume() ensures fresh data load
- StateFlow reactive updates reflect changes immediately

**Related Implementation:**
See ProfileScreen (View 1) for full details.

---

### 6. Edit Profile Screen

**File:** `app/src/main/java/com/example/ui/editprofile/EditProfileScreen.kt`

**Purpose:** Edit and persist restaurant profile data

**Features Shown:**
- ✅ Form with TextFields for:
  - Restaurant name (nama restoran)
  - Address (alamat)
  - Description (deskripsi)
  - Operating hours (jam buka)
- ✅ TextFields pre-populated with current data from SharedPreferences
- ✅ "Simpan" button:
  - Validates all fields are non-empty
  - Writes data to SharedPreferences via ViewModel
  - Navigates back to ProfileScreen
  - ProfileScreen shows updated data immediately
- ✅ "Batal" button:
  - Cancels edit without saving
  - Navigates back to ProfileScreen
- ✅ Form validation (all fields required before save)

**Data Flow:**
1. User navigates to EditProfileScreen from ProfileScreen
2. Current profile data loaded into TextFields from SharedPreferences
3. User modifies any fields
4. User clicks "Simpan"
5. ViewModel calls PreferencesManager to save all fields:
   - `PreferencesManager.saveRestoranName(nama)`
   - `PreferencesManager.saveRestoranAlamat(alamat)`
   - `PreferencesManager.saveRestoranDeskripsi(deskripsi)`
   - `PreferencesManager.saveRestoranJamBuka(jamBuka)`
6. Navigation pops back to ProfileScreen
7. ProfileScreen LaunchedEffect reloads data
8. New profile data displays

**Technical Details:**
- Form state managed with remember { mutableStateOf } (meets assignment requirement)
- Validation: Save button disabled until all fields are non-empty
- Initial values loaded in LaunchedEffect
- Smooth navigation transition with animations

**Assignment Coverage:**
- Meets: EditProfileScreen requirement (TextFields, Save/Cancel, persistence, data refresh)

**Related Code Files:**
- `data/PreferencesManager.kt` - Save profile methods:
  - `saveRestoranName()`, `saveRestoranAlamat()`, `saveRestoranDeskripsi()`, `saveRestoranJamBuka()`
- `ui/RestoranViewModel.kt` - `updateProfile()` method
- `ui/profile/ProfileScreen.kt` - Data reload on return

---

### 7. Dark Mode / Dark Theme

**File:** `app/src/main/java/com/example/ui/theme/Theme.kt`

**Purpose:** Demonstrate the dark theme across the application

**Features Shown:**
- ✅ Dark color scheme applied to entire app:
  - Dark background colors
  - Light text for readability
  - Adjusted primary/secondary colors for dark mode
- ✅ All screens properly themed (Home, Menu, Detail, Profile, EditProfile)
- ✅ Same functionality as light mode, just different colors
- ✅ Smooth transition between light and dark modes

**Dark Mode Color Palette:**
- Primary: `#FFB347` (Light Orange)
- Secondary: `#5F9EA0` (Cadet Blue)
- Tertiary: `#CDAA7D` (Sandy Brown)
- Background: Dark surface colors
- On Surface: Light text colors

**Light Mode Color Palette:**
- Primary: `#FF8C42` (Warm Orange)
- Secondary: `#20B2AA` (Light Sea Green)
- Tertiary: `#FFB700` (Amber)
- Background: Light surface colors
- On Surface: Dark text colors

**How Theme Switching Works:**
1. User clicks theme toggle in ProfileScreen
2. `viewModel.toggleTheme()` is called
3. ViewModel updates `isDarkMode` StateFlow
4. ViewModel saves theme choice to SharedPreferences (`theme_mode` key)
5. All screens recompose with new color scheme
6. On app restart, theme is restored from SharedPreferences

**Technical Implementation:**
```kotlin
// In Theme.kt
@Composable
fun RestoranKitaTheme(
    isDarkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) {
        DarkColorScheme  // Dark colors
    } else {
        LightColorScheme // Light colors
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// In MainActivity.kt
setContent {
    val viewModel: RestoranViewModel = viewModel()
    val isDarkMode = viewModel.isDarkMode.collectAsState().value
    
    RestoranKitaTheme(isDarkTheme = isDarkMode) {
        NavGraph(navController)
    }
}
```

**Assignment Coverage:**
- Meets: Vibe Coding - "Tema gelap/terang dengan toggle yang disimpan di SharedPreferences"

**Related Code Files:**
- `ui/theme/Color.kt` - Color definitions for light and dark modes
- `ui/theme/Theme.kt` - Theme composition
- `data/PreferencesManager.kt` - Theme persistence: `getThemeMode()`, `setThemeMode()`
- `ui/RestoranViewModel.kt` - Theme state: `isDarkMode` StateFlow

---

## 🎨 Design System

### Color Palette

**Light Mode:**
- Primary: Orange (#FF8C42) - Warm, inviting restaurant feel
- Secondary: Light Sea Green (#20B2AA) - Fresh, complementary
- Tertiary: Amber (#FFB700) - Food-related warmth
- Background: White/Light Gray - Clean, readable

**Dark Mode:**
- Primary: Light Orange (#FFB347) - Warmer in dark mode
- Secondary: Cadet Blue (#5F9EA0) - Softer blue for dark
- Tertiary: Sandy Brown (#CDAA7D) - Earthy, warm
- Background: Dark Gray/Black - Easy on eyes
- Text: Light gray/white - Readable on dark background

### Typography

Material Design 3 typography system:
- Display sizes for major headings
- Title sizes for screen titles
- Body sizes for descriptions
- Label sizes for buttons and labels

---

## 🔄 Navigation Flow Visualization

```
                    ┌─────────────────┐
                    │   HomeScreen    │
                    │  (restaurant name)
                    │  buttons: Menu  │
                    │           Profile
                    └────┬───────┬────┘
                         │       │
          ┌──────────────┘       └──────────────┐
          │                                      │
    ┌─────▼──────┐                        ┌─────▼────────┐
    │ MenuScreen │                        │ProfileScreen │
    │ (5+ items) │                        │ (all data)   │
    │ search     │                        │ theme toggle │
    │ filter     │                        │ edit button  │
    └────┬───────┘                        └──────────────┘
         │                                      │
         │ click item                           │ edit button
         │                                      │
    ┌────▼──────────────────┐        ┌─────────▼──────┐
    │DetailMenuScreen       │        │EditProfileScreen
    │ (full details)        │        │ (form, save/  │
    │ 5-star rating ⭐⭐⭐  │        │  cancel)       │
    │ back button           │        └────────────────┘
    └──────────────────────┘
         │ back button
         └─────────────────── ProfileScreen
```

---

## ✅ Assignment Requirements Met

### Core Requirements
- [x] **5 Screens**: Home, Menu, DetailMenu, Profile, EditProfile
- [x] **Navigation**: Jetpack Navigation Compose with NavHost
- [x] **SharedPreferences**: Full implementation in all screens
- [x] **State Management**: ViewModel + StateFlow + Compose State
- [x] **Data Persistence**: Profile and ratings persisted

### Vibe Coding Features (4+ implemented)
- [x] **1. Dark/Light Theme** - Toggle in ProfileScreen, persisted in SharedPreferences
- [x] **2. Screen Animations** - slideInHorizontally + fadeIn on all transitions
- [x] **3. Star Rating System** - Interactive 5-star rating in DetailMenuScreen
- [x] **4. Search & Filter** - Real-time search and category filtering in MenuScreen
- [x] **5. Shopping Cart** - Bonus full cart system (CartScreen)

### Technical Requirements
- [x] Single-activity architecture
- [x] Jetpack Compose UI
- [x] Material Design 3
- [x] Kotlin coroutines
- [x] Type-safe state management

---

## 📝 Summary

The Restoran Kita application successfully demonstrates:
- ✅ Professional Android architecture (MVVM)
- ✅ Modern Jetpack libraries (Navigation, Compose, ViewModel)
- ✅ Persistent data storage (SharedPreferences)
- ✅ Reactive state management (StateFlow)
- ✅ Creative UI/UX enhancements (Vibe Coding)
- ✅ All UTS assignment requirements met
- ✅ Production-ready code quality

The screenshots showcase a cohesive, functional restaurant management application with smooth navigation, persistent data, and thoughtful design choices.
