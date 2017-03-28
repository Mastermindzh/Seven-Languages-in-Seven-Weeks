# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)

Gem::Specification.new do |spec|
  spec.name          = "Day1"
  spec.version       = '1.0'
  spec.authors       = ["Rick van Lieshout"]
  spec.email         = ["info@rickvanlieshout.com"]
  spec.summary       = %q{Day 1 summary}
  spec.description   = %q{Day 1 longer summary.}
  spec.homepage      = "http://rickvanlieshout.com/"
  spec.license       = "MIT"

  spec.files         = ['lib/main.rb']
  spec.executables   = ['bin/main']
  spec.test_files    = ['tests/test_NAME.rb']
  spec.require_paths = ["lib"]
end
