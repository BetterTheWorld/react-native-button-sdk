require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |spec|
  spec.name         = "react-native-button-sdk"
  spec.version      = package["version"]
  spec.summary      = package["description"]
  spec.homepage     = "https://github.com/BetterTheWorld/react-native-button-sdk"
  spec.license      = "MIT"
  spec.authors      = { package['author']['name'] => package['author']['email'] }
  spec.platforms    = { ios: "9.0" }
  spec.source       = { git: "https://github.com/BetterTheWorld/react-native-button-sdk.git", tag: "#{spec.version}" }

  spec.source_files = "ios/**/*.{h,m,swift}"
  spec.requires_arc = true

  spec.dependency "React"
  spec.dependency "Button", "~> 6"
end

