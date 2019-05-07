# SimpleBottomNavigation

SimpleBottomNavigation is minimal and light-weight android library, utilizable in simple way.

## Getting Started

Clone the repository along with the demo app. Add the bottomnav Directory to your project and you will be good to go.

### Example

What things you need to install the software and how to install them

```
		/*  feedbackOverlayMode : Allowed Values are "ADD", "CLEAR", "DARKEN", "DST", "DST_ATOP", "DST_IN", "DST_OUT", "DST_OVER", "LIGHTEN", "MULTIPLY", "OVERLAY",
        "SCREEN", "SRC", "SRC_ATOP", "SRC_IN", "SRC_OUT", "SRC_OVER", "XOR" */

        mContainer = findViewById(R.id.container);
        mIconList = new ArrayList<>();
        mTitleList = new ArrayList<>();

        mIconList.add(getResources().getDrawable(R.drawable.home));
        mIconList.add(getResources().getDrawable(R.drawable.blend));
        mIconList.add(getResources().getDrawable(R.drawable.border));
        mIconList.add(getResources().getDrawable(R.drawable.camera));
        mIconList.add(getResources().getDrawable(R.drawable.colorbucket));
        mIconList.add(getResources().getDrawable(R.drawable.config));
        mIconList.add(getResources().getDrawable(R.drawable.effect));
        mIconList.add(getResources().getDrawable(R.drawable.face));
        mIconList.add(getResources().getDrawable(R.drawable.heart));
        mIconList.add(getResources().getDrawable(R.drawable.invert));
        mIconList.add(getResources().getDrawable(R.drawable.opacity));
        mIconList.add(getResources().getDrawable(R.drawable.rotate));
        mIconList.add(getResources().getDrawable(R.drawable.text));

        mTitleList.add("Home");
        mTitleList.add("Blend");
        mTitleList.add("Border");
        mTitleList.add("Camera");
        mTitleList.add("Bucket");
        mTitleList.add("Config");
        mTitleList.add("Effect");
        mTitleList.add("Emoji");
        mTitleList.add("Rate");
        mTitleList.add("Invert");
        mTitleList.add("Opacity");
        mTitleList.add("Rotate");
        mTitleList.add("Text");

        /*Overlay Color Image, This Overlay will be applied on click of menu Item*/
        mOverlay = BitmapFactory.decodeResource(MainActivity.this.getResources(),
                R.drawable.overlay);

        BottomNavigationView nav = new BottomNavigationView.Builder(MainActivity.this)
                .setIconList(mIconList)
                .setTitleList(mTitleList)
                .setFeedbackOverlay(mOverlay)
                .setFeedbackOverlayMode("DARKEN")
                .isViewAnimated(true)
                .isIconAnimated(false)
                .setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(MainActivity.this, ""+position, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

        mContainer.addView(nav);

```

## Authors

* **Kiran Maniya** - *Initial work* - [Trinity InfoSystem](http://www.trinityinfosystem.com/)
* **Krishna Vyas** - *Co-contributer* - [Trinity InfoSystem](http://www.trinityinfosystem.com/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
