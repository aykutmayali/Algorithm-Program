# Hide Favicons (Chrome Extension)

Bu extension, ziyaret edilen sayfalardaki favicon tanımını şeffaf bir ikonla değiştirir.
Böylece sekmelerde görünen site ikonları gizlenmiş olur.

## Kurulum

1. `chrome://extensions` adresini açın.
2. Sağ üstten **Developer mode**'u açın.
3. **Load unpacked** butonuna tıklayın.
4. Bu klasörü seçin:
   - `tools/chrome-hide-favicon-extension`

## Notlar

- Bazı sayfalarda favicon kısa süreli görünüp sonra kaybolabilir (sayfa çok erken ikon set ederse).
- Dahili Chrome sayfalarında (`chrome://...`) content script çalışmaz.
- Extension kaldırıldığında favicon davranışı normale döner.
